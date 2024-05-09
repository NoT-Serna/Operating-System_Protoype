/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

import ur_os.process.Process;

/**
 *
 * @author super
 */
public class FirstFitMemorySlotManager extends FreeMemorySlotManager{

    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot m = null;
        //To do
        for(MemorySlot slot : list){
            if(slot.getSize() >= size){
                slot.setSize(slot.getSize()-size);
                m = new MemorySlot(slot.getBase()+slot.getSize(),size);
                break;
            }
        }
        return m;
    }
}
