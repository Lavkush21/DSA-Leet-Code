class Solution {
    public int numberOfSteps(int num) {
        if (num == 0) return 0;
        
        int steps = 0;
        while (num > 0) {
            // If the last bit is 1, it's odd (takes 1 step to subtract).
            // If it's even, it takes 1 step to divide.
            steps += (num & 1) == 1 ? 2 : 1;
            num >>= 1;
        }
        
        // Subtract 1 because the very last bit (most significant 1) 
        // only needs to be subtracted to reach 0, not divided.
        return steps - 1; 
    }
}
