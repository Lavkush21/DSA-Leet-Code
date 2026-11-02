import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Sort the array to easily find the median
        Arrays.sort(nums);
        
        // The median minimizes the sum of absolute deviations
        int median = nums[nums.length / 2];
        int moves = 0;
        
        // Sum the absolute difference between each element and the median
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
