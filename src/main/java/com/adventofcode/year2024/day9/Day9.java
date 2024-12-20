package com.adventofcode.year2024.day9;

import java.util.*;

public class Day9 {

    record DiskFile(int id, int size, int startLoc) {}

    // id is just a counter within the file, not unique on its own
    record DiskFileBlock(int fileId, int id) {}

    //Set<DiskFile> diskFiles = new HashSet<>();

    List<Optional<DiskFileBlock>> diskBlocks = new ArrayList<>();
    public Day9(String diskMap) {
        convertDiskMapToFileLayout(diskMap);
    }

    private void convertDiskMapToFileLayout(String diskMap) {
        int fileIdCounter = 0;

        for (int diskMapPointer = 0; diskMapPointer < diskMap.length(); diskMapPointer++) {
            int current = diskMap.charAt(diskMapPointer) - '0';
            if (current < 0 || current > 9) {
                throw new IllegalArgumentException("diskMap contains invalid input");
            }
            if (diskMapPointer % 2 == 0) {
                // dealing with a file
                //DiskFile df = new DiskFile(fileIdCounter, current);
                for (int i = 0; i < current; i++) {
                    DiskFileBlock dfb = new DiskFileBlock(fileIdCounter, i);
                    diskBlocks.add(Optional.of(dfb));
                }
                fileIdCounter++;
            } else {
                // dealing with free space
                for (int i = 0; i < current; i++) {
                    diskBlocks.add(Optional.empty());
                }
            }
        }
    }

    public String getDiskBlockLayout() {
        StringBuilder sb = new StringBuilder();

        int pos = 0;
        for(Optional<DiskFileBlock> diskBlock : diskBlocks) {
            if (diskBlock.isEmpty()) {
                sb.append('.');
            } else {
                sb.append(diskBlock.get().fileId % 10);
            }
        }
        return sb.toString();
    }

    public void compactHDDPart1() {
        // Used for finding free blocks at the start of the disk
        int diskPointer1 = 0;
        // Used for finding used blocks to move
        int diskPointer2 = diskBlocks.size() - 1;

        //System.out.println(getDiskBlockLayout());
        while (diskPointer1 < diskPointer2) {
            while (diskBlocks.get(diskPointer1).isPresent()) {
                diskPointer1++;
            }
            while (diskBlocks.get(diskPointer2).isEmpty()) {
                diskPointer2--;
            }
            if (diskPointer2 <= diskPointer1) {
                break;
            }
            swapBlocks(diskPointer1, diskPointer2);

            //System.out.println(getDiskBlockLayout());
            diskPointer1++;
            diskPointer2--;
        }
    }

    public void compactHDDPart2() {
        // Used for finding used blocks/files to move
        int diskPointer2 = diskBlocks.size() - 1;

        //System.out.println(getDiskBlockLayout());

        while(diskPointer2 > 0) {
            DiskFile fileToMove = findNextDiskFile(diskPointer2);

            //System.out.println(getDiskBlockLayout());

            diskPointer2 = fileToMove.startLoc - 1;
            int loc = findContiguousFreeSpace(fileToMove.size, fileToMove.startLoc);
            if (loc >= 0) {
                // We can move it as we found some free space for it to go to
                moveFile(fileToMove, loc);
                //System.out.println(getDiskBlockLayout());
            }
        }
    }

    private DiskFile findNextDiskFile(int startLoc) {
        // Used for finding used blocks/files to move
        int diskPointer2 = startLoc;

        while (diskBlocks.get(diskPointer2).isEmpty()) {
            diskPointer2--;
        }
        int endBlock = diskPointer2;
        int currFileId = diskBlocks.get(diskPointer2).get().fileId;
        while (diskPointer2 >= 0 && diskBlocks.get(diskPointer2).isPresent() && diskBlocks.get(diskPointer2).get().fileId == currFileId) {
            diskPointer2--;
        }
        int startBlock = diskPointer2 + 1;
        int blocksRequired = 1 + endBlock - startBlock;

        return new DiskFile(currFileId, blocksRequired, startBlock);
    }

    /**
     * Find the first instance of contiguous free space of at least size
     * @param size the number of free blocks required
     * @param stopLoc stop if you don't find the free blocks required by location
     * @return -1 if insufficient free space, otherwise index of first block of free space of at least size
     */
    private int findContiguousFreeSpace(int size, int stopLoc) {
        // Used for finding free blocks at the start of the disk
        int diskPointer1 = 0;
        int possStartBlock = -1;
        int freeBlocksFound = 0;

        while (diskPointer1 < stopLoc) {
            if (diskBlocks.get(diskPointer1).isEmpty()) {
                if (possStartBlock == -1) {
                    possStartBlock = diskPointer1;
                }
                freeBlocksFound++;
                if (freeBlocksFound == size) {
                    return possStartBlock;
                }
            } else {
                possStartBlock = -1;
                freeBlocksFound = 0;
            }

            diskPointer1++;
        }

        return -1;
    }

    private void moveFile(DiskFile fileToMove, int loc) {
        int endLoc = fileToMove.startLoc + fileToMove.size - 1;
        for (int diskPointer1 = loc, diskPointer2 = fileToMove.startLoc; diskPointer2 <= endLoc; diskPointer1++, diskPointer2++) {
            swapBlocks(diskPointer1, diskPointer2);
        }
    }

    private void swapBlocks(int loc1, int loc2) {
        Optional<DiskFileBlock> diskFileBlock1 = diskBlocks.get(loc1);
        Optional<DiskFileBlock> diskFileBlock2 = diskBlocks.get(loc2);
        diskBlocks.set(loc1, diskFileBlock2);
        diskBlocks.set(loc2, diskFileBlock1);
    }

    public long getFilesystemChecksum() {
        long checksum = 0L;

        for (int diskPointer = 0; diskPointer < diskBlocks.size(); diskPointer++) {
            Optional<DiskFileBlock> diskFileBlock = diskBlocks.get(diskPointer);
            if (diskFileBlock.isEmpty()) {
                continue;
            }

            checksum += (long)diskPointer * diskFileBlock.get().fileId();
        }

        return checksum;
    }
}
