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
        int min = Integer.MAX_VALUE; //Maximizes the min value to the highest possbile value

        if (!processes.isEmpty() && cpuEmpty) {  //Create a shortestProcess that points to null
            Process shortestProcess = null;
        //Finding the shortest process based on the remaining burst time
        for (Process p : processes) {
            if (p.getRemainingTimeInCurrentBurst() < min) {
                min = p.getRemainingTimeInCurrentBurst();
                shortestProcess = p;
            }
        }

        // Once the shortest process is found, remove the process from the list
        if (shortestProcess != null) {
            processes.remove(shortestProcess);
            // Have the process go to CPU
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, shortestProcess);
        }
    }
}
    
    
    @Override
    public void newProcess(boolean cpuEmpty) {} 

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {}
    
}
