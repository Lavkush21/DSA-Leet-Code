import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row numbers to a bitmask representing reserved positions
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // We only care about seats 2 through 9
            if (col >= 2 && col <= 9) {
                // Set the bit corresponding to the seat column
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | (1 << col));
            }
        }
        
        // Start by assuming all rows are completely empty (2 families per row)
        int maxFamilies = 2 * n;
        
        // Define bitmasks for the three valid 4-seat blocks
        // Left combination (seats 2,3,4,5): 1<<2 | 1<<3 | 1<<4 | 1<<5 = 4 + 8 + 16 + 32 = 60
        int leftMask = 60; 
        // Right combination (seats 6,7,8,9): 1<<6 | 1<<7 | 1<<8 | 1<<9 = 64 + 128 + 256 + 512 = 960
        int rightMask = 960;
        // Middle combination (seats 4,5,6,7): 1<<4 | 1<<5 | 1<<6 | 1<<7 = 16 + 32 + 64 + 128 = 240
        int middleMask = 240;
        
        // Process only the rows that have at least one reservation
        for (int mask : rowMasks.values()) {
            boolean leftValid = (mask & leftMask) == 0;
            boolean rightValid = (mask & rightMask) == 0;
            
            if (leftValid && rightValid) {
                // Both sides are free; it can still hold 2 families (no reduction)
                continue;
            } else if (leftValid || rightValid || (mask & middleMask) == 0) {
                // Only one of the 3 combinations is valid; row can only hold 1 family
                maxFamilies -= 1;
            } else {
                // No combinations are valid; row holds 0 families
                maxFamilies -= 2;
            }
        }
        
        return maxFamilies;
    }
}
