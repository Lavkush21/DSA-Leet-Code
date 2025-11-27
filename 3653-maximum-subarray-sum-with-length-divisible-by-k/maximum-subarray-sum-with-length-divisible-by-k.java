import java.util.Arrays;

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
       
        long max_sum = Long.MIN_VALUE;
        long current_prefix_sum = 0;
      
        long[] min_prefix_sums = new long[k];
        Arrays.fill(min_prefix_sums, Long.MAX_VALUE);
        
        
        min_prefix_sums[0] = 0;
        
        for (int i = 0; i < nums.length; i++) {
            current_prefix_sum += nums[i];
         
            int remainder = (i + 1) % k;
           
            if (min_prefix_sums[remainder] != Long.MAX_VALUE) {
               
                long potential_sum = current_prefix_sum - min_prefix_sums[remainder];
                max_sum = Math.max(max_sum, potential_sum);
            }
            
            
            min_prefix_sums[remainder] = Math.min(min_prefix_sums[remainder], current_prefix_sum);
        }
        
      
        return max_sum;
    }
}
