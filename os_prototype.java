class os_prototype {

}

class System_OS{
    float clock;
    float timer;
}

class CPU{
    System_OS c1 = new System_OS();

}

class NewProcessQueue{
    System_OS c1;
}

class IO_queue{
    System_OS c1;
}

class Ready_Queue{
    System_OS c1;
}

class ProcState{
    enum State{
        READY, NEW, CPU , IO, TERMINATED
    }
    State currentState;

    public ProcState(){
        currentState = State.NEW;
    }
}

class Process{
    ProcState.State state;
    int id;
    int timeln;
    int priority;

    public Process(int id, int timeln, int priority){
        this.id = id;
        this.timeln = timeln;
        this.priority = priority;
        this.state = ProcState.State.NEW;
    }

}

class Scheduler{
    public Process get_next(){
    }
}







