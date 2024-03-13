/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

/**
 *
 * @author prestamour
 */
public class RoundRobin extends Scheduler{

    int q;
    int cont;
    
    RoundRobin(OS os){
        super(os);
        q = 5;
        cont=0;
    }
    
    RoundRobin(OS os, int q){
        this(os);
        this.q = q;
    }
    
   
    @Override
    public void getNext(boolean cpuEmpty) {
        if(!processes.isEmpty() && cpuEmpty){
            Process nextProcess = processes.poll();
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, nextProcess);
            
            int burst_time = nextProcess.getRemainingTimeInCurrentBurst();
            if(burst_time <= q){
                nextProcess.setRemainingTimeInCurrentBurst(0);
                
            } else{
                nextProcess.setRemainingTimeInCurrentBurst(burst_time);
                processes.offer(nextProcess);
            }
            
        }
        
        
        
    }
    
    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event
    
}

/*
Processs method:     
public void setRemainingTimeInCurrentBurst(int new_time){
        this.new_time = pbl.getRemainingTimeInCurrentBurst();
    }
*/