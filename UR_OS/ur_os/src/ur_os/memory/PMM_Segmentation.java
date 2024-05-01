package ur_os.memory;

import ur_os.process.ProcessMemoryManager;
import ur_os.process.ProcessMemoryManagerType;

/**
 * 
 * @author Serna
 */
public class PMM_Segmentation extends ProcessMemoryManager {
    
    int base;
    int limit;
    PageTable pt;
    

    public PMM_Segmentation() {
        this.base = 0;
        this.limit = 100;
        this.pt = null;
    }
    
    public PMM_Segmentation(int base, int limit, PageTable pt){
         super(ProcessMemoryManagerType.SEGMENTATION, limit);
         this.base = base;
         this.limit = limit;
         pt = new PageTable(limit);
         
    }
    
    public PMM_Segmentation(PMM_Segmentation pmm){
        super(pmm);
        if(pmm.getType() == this.getType()){
            this.base = pmm.base;
            this.limit = pmm.limit;
            pt = new PageTable(limit);
        }else{
            System.out.println("Error - Wrong PMM parameter");
        }
    }
    
    public int getBase(){
        return base;
    }
    
    public void setBase(int base){
        this.base = base;
    }
    
    public int getLimit(){
        return limit;
    }
    
    public void setLimit(int limit){
        this.limit = limit;
    }
    
    public PageTable getPt(){
        return pt;
    }
    
    public void addFrameID(int frame){
        pt.addFrameID(frame);
    }

    public MemoryAddress getPageMemoryAddressFromLocalAddress(int localAddress){
        if(localAddress < getLimit()){
            int page_num = localAddress / PageTable.getPageSize();
            int offset = localAddress + getBase();
            return new MemoryAddress(page_num, offset);
        }else{
            return null;
        }
    }
    
     public MemoryAddress getFrameMemoryAddressFromLogicalMemoryAddress(MemoryAddress m){
        int frame_id = pt.getFrameIdFromPage(m.getPage_frame());
        int offset = m.getOffset();
        return new MemoryAddress(frame_id,offset);
    }

    @Override
    public int getPhysicalAddress(int logicalAddress) {
        MemoryAddress page_address = getPageMemoryAddressFromLocalAddress(logicalAddress);
        MemoryAddress frame_address = getFrameMemoryAddressFromLogicalMemoryAddress(page_address);
        return (frame_address.getPage_frame() * PageTable.getPageSize()) + frame_address.getOffset();
    }
    
         @Override
    public String toString(){
        return pt.toString();
    }
    
}
