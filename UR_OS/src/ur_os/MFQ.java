/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;


import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author prestamour
 */
public class MFQ extends Scheduler{
    
    private final Queue<Process> highPriorityQueue;
    private final Queue<Process> mediumPriorityQueue;
    private final Queue<Process> lowPriorityQueue;
  
    int currentScheduler;
    
    private ArrayList<Scheduler> schedulers;
    //This may be a suggestion... you may use the current sschedulers to create the Multilevel Feedback Queue, or you may go with a more tradicional way
    //based on implementing all the queues in this class... it is your choice. Change all you need in this class.
    
    MFQ(OS os){
        super(os);
        currentScheduler = -1;
        schedulers = new ArrayList();
        highPriorityQueue = new LinkedList<>();
        mediumPriorityQueue = new LinkedList<>();
        lowPriorityQueue = new LinkedList<>();

        
        
    }
    
    MFQ(OS os, Scheduler... s){ //Received multiple arrays
        this(os);
        schedulers.addAll(Arrays.asList(s));
        if(s.length > 0)
            currentScheduler = 0;
    }
        
    @Override
    public void addProcess(Process p){
        schedulers.get(0).addProcess(p);
        
    }
    
    /*
    void defineCurrentScheduler(){
        //This methos is siggested to help you find the scheduler that should be the next in line to provide processes... perhaps the one with process in the queue?
        for(int i = 0; i<schedulers.size(); i++){
            if(!schedulers.get(i).isEmpty()){
                currentScheduler = i;
                return;
            }
        }
        currentScheduler = -1;
    }
    */
    
  

    @Override
   public void getNext(boolean cpuEmpty) {
    if (!cpuEmpty) {
        // If the CPU is not empty, do nothing and return
        return;
    }
    
    // Otherwise, check the queues starting from the highest priority
    if (!highPriorityQueue.isEmpty()) {
        executeNextProcess(highPriorityQueue);
    } else if (!mediumPriorityQueue.isEmpty()) {
        executeNextProcess(mediumPriorityQueue);
    } else if (!lowPriorityQueue.isEmpty()) {
        executeNextProcess(lowPriorityQueue);
    }
}
    
     private void executeNextProcess(Queue<Process> queue) {
        Process nextProcess = queue.poll();
        os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, nextProcess);
    }
    

    
    @Override
    public void newProcess(boolean cpuEmpty) {}
    //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event
    
}