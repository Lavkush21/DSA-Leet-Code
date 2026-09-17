class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        
        // minLens[i] stores the minimum length of a valid subarray 
        // that ends at or before index i.
        int[] minLens = new int[n];
        
        // Initialize the array with a large value representing infinity
        int INF = Integer.MAX_VALUE;
        java.util.Arrays.fill(minLens, INF);
        
        int left = 0;
        int currentSum = 0;
        int minTotalLength = INF;
        int bestLenSoFar = INF;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink the window from the left if the current sum exceeds the target
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            // If we found a valid subarray matching the target
            if (currentSum == target) {
                int currentLen = right - left + 1;
                
                // Check if there is a valid non-overlapping subarray before 'left'
                if (left > 0 && minLens[left - 1] != INF) {
                    minTotalLength = Math.min(minTotalLength, currentLen + minLens[left - 1]);
                }
                
                // Update the shortest single subarray found up to the current window
                bestLenSoFar = Math.min(bestLenSoFar, currentLen);
            }
            
            // Record the best single subarray length ending at or before 'right'
            minLens[right] = bestLenSoFar;
        }
        
        // Return the minimum total length if found, otherwise return -1
        return minTotalLength == INF ? -1 : minTotalLength;
    }
}
