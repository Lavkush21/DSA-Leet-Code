class Solution {
   
    public int countPartitions(int[] nums) {
        int n = nums.length;
        // The problem requires at least two elements to form valid left and right non-empty subarrays.
        if (n < 2) {
            return 0;
        }

        long totalSum = 0;
       
        for (int num : nums) {
            totalSum += num;
        }

       
        if (totalSum % 2 == 0) {
            // If the total sum is even, all n-1 possible partitions are valid.
            return n - 1;
        } else {
            // If the total sum is odd, no partitions will yield an even difference.
            return 0;
        }
    }
}
