package ur_os;

import java.util.ArrayList;

public class SJF extends Scheduler {

    public SJF(OS os) {
        super(os);
    }

    @Override
    public void getNext(boolean cpuBusy) {
        if(!processes.isEmpty() && os.isCPUEmpty()) {
            ArrayList<Integer> jobs = new ArrayList<>();
            for (Process p : processes) {
                ArrayList<ProcessBurst> bursts = p.getPBL().bursts; // get burst time ??
                int burst_time = bursts.size();
                jobs.add(burst_time);
            }
            int shortestJob = jobs.getFirst();
            for (Integer job : jobs) {
                if (job < shortestJob) {
                    shortestJob = job;
                }
            }
            Process p = processes.get(shortestJob);
            processes.remove(shortestJob); // removes shortest job from LinkedList
            os.interrupt(InterruptType.SCHEDULER_TO_CPU, p);
        }
    }
}
