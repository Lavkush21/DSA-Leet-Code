import java.util.HashMap;
import java.util.Map;

class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long windowSum = 0;
        long maxAns = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            // Add the current element to the window
            windowSum += nums[i];
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);

            // Remove the element sliding out of the window
            if (i >= k) {
                int outElement = nums[i - k];
                windowSum -= outElement;
                freqMap.put(outElement, freqMap.get(outElement) - 1);
                if (freqMap.get(outElement) == 0) {
                    freqMap.remove(outElement);
                }
            }

            // If the window has size k and all elements are distinct
            if (i >= k - 1 && freqMap.size() == k) {
                maxAns = Math.max(maxAns, windowSum);
            }
        }

        return maxAns;
    }
}