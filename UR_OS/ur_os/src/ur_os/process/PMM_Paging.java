/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ur_os;
import ur_os.process.ProcessMemoryManager;
import ur_os.process.ProcessMemoryManagerType;

/**
 *
 * @author super
 */
public class PMM_Paging extends ProcessMemoryManagement {
    PageTable pt;

    public PMM_Paging(int processSize) {
        super(ProcessMemoryManagementType.PAGING, processSize);
        pt = new PageTable(processSize);
    }

    public PMM_Paging(PageTable pt) {
        this.pt = pt;
    }

    public PageTable getPt() {
        return pt;
    }

    public MemoryAddress getPageMemoryAddressFromLocalAddress(int locAdd) {
        // Calculate page number and offset
        int pageNumber = locAdd / PageTable.getPageSize();
        int offset = locAdd % PageTable.getPageSize();
        return new MemoryAddress(pageNumber, offset);
    }

    public MemoryAddress getFrameMemoryAddressFromLogicalMemoryAddress(MemoryAddress m) {
        // Find the frame number based on the memory address
        int frameNumber = pt.getFrameIdFromPage(m.getPage_frame());
        if (frameNumber == -1) {
            // Page fault, frame not found
            return null;
        }
        int offset = m.getOffset();
        return new MemoryAddress(frameNumber, offset);
    }

    public int getPhysicalAddress(int logicalAddress) {
        MemoryAddress logicalMemoryAddress = getPageMemoryAddressFromLocalAddress(logicalAddress);
        MemoryAddress frameMemoryAddress = getFrameMemoryAddressFromLogicalMemoryAddress(logicalMemoryAddress);
        if (frameMemoryAddress == null) {
            // Page fault, frame not found
            return -1;
        }
        int frameNumber = frameMemoryAddress.getPage_frame();
        int offset = frameMemoryAddress.getOffset();
        // Calculate physical address
        return frameNumber * PageTable.getPageSize() + offset;
    }

    // Method to load a page into a specific frame
    public void loadPageToFrame(int pageNumber, int frameNumber) {
        pt.setFrameId(pageNumber, frameNumber);
    }
}