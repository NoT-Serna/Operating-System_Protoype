/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.paging;

import ur_os.memory.MemoryAddress;
import ur_os.process.ProcessMemoryManager;
import ur_os.process.ProcessMemoryManagerType;

/**
 *
 * @author super
 */
public class PMM_Paging extends ProcessMemoryManager{

    PageTable pt;

    public PMM_Paging(int processSize) {
        super(ProcessMemoryManagerType.PAGING,processSize);
        pt = new PageTable(processSize);
    }

    public PMM_Paging(PageTable pt) {
        this.pt = pt;
    }

    public PMM_Paging(PMM_Paging pmm) {
        super(pmm);
        if(pmm.getType() == this.getType()){
            this.pt = new PageTable(pmm.getPt());
        }else{
            System.out.println("Error - Wrong PMM parameter");
        }
    }

    public PageTable getPt() {
        return pt;
    }

    public void addFrameID(int frame){
        pt.addFrameID(frame);
    }

    public MemoryAddress getPageMemoryAddressFromLocalAddress(int locAdd){
        int page_num = locAdd / PageTable.getPageSize();
        int offset = locAdd % PageTable.getPageSize();
        return new MemoryAddress(page_num, offset);
    }

    public MemoryAddress getFrameMemoryAddressFromLogicalMemoryAddress(MemoryAddress m){
        int frame_id = pt.getFrameIdFromPage(m.getDivision());
        int offset = m.getOffset();
        return new MemoryAddress(frame_id,offset);
    }

    @Override
    public int getPhysicalAddress(int logicalAddress){

        MemoryAddress page_address = getPageMemoryAddressFromLocalAddress(logicalAddress);
        MemoryAddress frame_address = getFrameMemoryAddressFromLogicalMemoryAddress(page_address);
        return (frame_address.getDivision() * PageTable.getPageSize()) + frame_address.getOffset();
    }

    @Override
    public String toString(){
        return pt.toString();
    }

}