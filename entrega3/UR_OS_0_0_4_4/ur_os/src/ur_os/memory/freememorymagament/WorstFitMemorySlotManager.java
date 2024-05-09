/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

import java.util.ArrayList;

/**
 *
 * @author super
 */
public class WorstFitMemorySlotManager extends FreeMemorySlotManager{
    
    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot m = null;
        ArrayList<Integer> sizes = new ArrayList<>();
        for(MemorySlot slot : list){
            sizes.add(slot.getSize()-size);
        }
        int minIndex = 0;
        for (int i = 1; i < sizes.size(); i++) {
            if (sizes.get(i) >= 0 && sizes.get(i) > sizes.get(minIndex)) {
                minIndex = i;
            }
        }
        list.get(minIndex).setSize(list.get(minIndex).getSize()-size);
        m = new MemorySlot(list.get(minIndex).getBase()+list.get(minIndex).getSize(),size);
        return m;
    }

}
    

