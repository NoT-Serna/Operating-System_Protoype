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
    RoundRobin(OS os) {
        super(os);
        q = 5;
        cont = 0;
    }
    RoundRobin(OS os, int q) {
        this(os);
        this.q = q;
    }
    void resetCounter(){
        cont = 0;
    }

    @Override
    public void getNext(boolean cpuEmpty) {
        // 4 casos
        // 1. CPU vacio y hay procesos en la cola
        // 2. CPU vacio y no hay procesos en la cola
        // 3. CPU ocupado y hay procesos en la cola
        // 4. CPU ocupado y no hay procesos en la cola
        if (!cpuEmpty) { //cpu ocupada, verificar si cola vacia o no
            cont++;
            if(cont == q && !processes.isEmpty()){ // cola vacía
                os.interrupt(InterruptType.SCHEDULER_CPU_TO_RQ, processes.poll());
                cont = 0;
            }
        } else if(!processes.isEmpty()){ // cpu vacía, verificar queue
            os.interrupt(InterruptType.SCHEDULER_RQ_TO_CPU, processes.poll());
            cont=0;
        }
    }


    @Override
    public void newProcess(boolean cpuEmpty) {} //Non-preemtive in this event

    @Override
    public void IOReturningProcess(boolean cpuEmpty) {} //Non-preemtive in this event

}
