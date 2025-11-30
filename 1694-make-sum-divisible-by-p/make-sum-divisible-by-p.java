import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubarray(int[] nums, int p) {

        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        int targetRemainder = (int) (totalSum % p);
        if (targetRemainder == 0) {
            return 0;
        }
        Map<Integer, Integer> prefixToIndex = new HashMap<>();
        prefixToIndex.put(0, -1); // Initialize with 0 remainder at index -1
        
        int currentPrefixSumModP = 0;
        int minLen = nums.length;
        for (int i = 0; i < nums.length; i++) {
            currentPrefixSumModP = (currentPrefixSumModP + nums[i]) % p;
            int requiredRemainder = (currentPrefixSumModP - targetRemainder + p) % p;

            if (prefixToIndex.containsKey(requiredRemainder)) {
                minLen = Math.min(minLen, i - prefixToIndex.get(requiredRemainder));
            }
            prefixToIndex.put(currentPrefixSumModP, i);
        }
        return minLen == nums.length ? -1 : minLen;
    }
}
