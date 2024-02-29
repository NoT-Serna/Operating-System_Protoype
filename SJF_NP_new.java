/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class SJF_NP extends Scheduler{

    
    SJF_NP(OS os){
        super(os);
    }
    
   
    @Override
    public void getNext(boolean cpuEmpty) {
        int min = Integer.MAX_VALUE;

        if (!processes.isEmpty() && cpuEmpty) {
            Process shortestProcess = null;

        for (Process p : processes) {
            if (p.getRemainingTimeInCurrentBurst() < min) {
                min = p.getRemainingTimeInCurrentBurst();
                shortestProcess = p;
            }
        }

        if (shortestProcess != null) {
            processes.remove(shortestProcess);

            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, shortestProcess);
        }
    }
}
    
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive
    
}
