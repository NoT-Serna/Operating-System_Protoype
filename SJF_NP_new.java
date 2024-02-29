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
        int min = 0;
        if(!processes.isEmpty() && cpuEmpty){
            for(Process p: processes){
                if(p.getRemainingTimeInCurrentBurst() < min){
                    min = p.getRemainingTimeInCurrentBurst();
                }
            }
            Process p = processes.get(min);
            processes.remove();
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU,p);
        }
    }
    
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive
    
}
