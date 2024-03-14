/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author prestamour
 */
public class MFQ extends Scheduler{

    int currentScheduler;

    private ArrayList<Scheduler> schedulers; //This is the list of schedulers that will be used in the MFQ
    //This may be a suggestion... you may use the current schedulers to create the Multilevel Feedback Queue, or you may go with a more traditional way
    //based on implementing all the queues in this class... it is your choice. Change all you need in this class.

    MFQ(OS os){ // constructor for the MFQ with no schedulers
        super(os);
        currentScheduler = -1;
        schedulers = new ArrayList();
    }

    MFQ(OS os, Scheduler... s){ //Received multiple arrays
        this(os);
        schedulers.addAll(Arrays.asList(s));
        if(s.length > 0)
            currentScheduler = 0;
    }

    @Override
    public void addProcess(Process p) { //Overwriting the parent's addProcess(Process p) method may be necessary in order to decide what to do with process coming from the CPU.
        if (p != null && p.getState() == ProcessState.NEW) { // If the process is NEW, ADD IT TO THE FIRST QUEUE
            schedulers.getFirst().addProcess(p);
        } else if (p != null && p.getState() == ProcessState.IO) { // If the process is returning from IO, ADD IT TO THE FIRST QUEUE
            schedulers.getFirst().addProcess(p);
        } else if (p != null && p.getState() == ProcessState.CPU) { // if process comes from the cpu, move it to the next queue
            if (p.getCurrentScheduler() < schedulers.size()-1) { // check that the process is not in the last queue, if it is, stay on that last queue (do nothing)
                schedulers.get(p.getCurrentScheduler()+1).addProcess(p); // move the process to the next queue
            }
        }
    }

    void defineCurrentScheduler() {
        for (int i = 0; i < schedulers.size(); i++) {
            if (!schedulers.get(i).isEmpty()) {
                currentScheduler = i;
                break;
            } else {
                currentScheduler = -1;
            }
        }
    }


    @Override
    public void getNext(boolean cpuEmpty) {
        //Suggestion: now that you know on which scheduler a process is, you need to keep advancing that scheduler. If it a preemptive one, you need to notice the changes
        //that it may have caused and verify if the change is coherent with the priority policy for the queues.

        if(!cpuEmpty ) { //If the CPU is not empty, the scheduler needs to keep advancing the current scheduler
            if(!processes.isEmpty()) {
                Process p = os.getProcessInCPU();
                schedulers.get(p.getCurrentScheduler()).getNext();
                addProcess(p);
            }
        } else { //If the CPU is empty, the scheduler needs to find the next scheduler with a process to send to the CPU
            defineCurrentScheduler();
            if(currentScheduler != -1) {
                schedulers.get(currentScheduler).getNext();
            }

        }
    }

    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event

}
