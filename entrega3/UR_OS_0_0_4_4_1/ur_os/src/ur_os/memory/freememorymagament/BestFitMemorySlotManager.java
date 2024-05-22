/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author super
 */
public class BestFitMemorySlotManager extends FreeMemorySlotManager{

    @Override
    public MemorySlot getSlot(int size) {
        ArrayList<Integer> sizes = new ArrayList<>();
        for (MemorySlot slot : list) {
            sizes.add(slot.getSize() - size);
        }
        int minIndex = 0;
        for (int i = 1; i < sizes.size(); i++) {
            if (sizes.get(i) >= 0 && sizes.get(i) < sizes.get(minIndex) ) { // sizes.get(i) >= 0 is to check if the slot is big enough
                minIndex = i;
            }
        }
        list.get(minIndex).setSize(list.get(minIndex).getSize() - size);
        return new MemorySlot(list.get(minIndex).getBase() + list.get(minIndex).getSize(), size);
    }
}