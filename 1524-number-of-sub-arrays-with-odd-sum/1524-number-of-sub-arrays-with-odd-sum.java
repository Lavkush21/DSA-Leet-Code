class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int evenCount = 1; // Empty prefix sum (0) is even
        int oddCount = 0;
        int prefixSum = 0;
        long totalSubarrays = 0;

        for (int num : arr) {
            prefixSum += num;

            if (prefixSum % 2 != 0) {
                totalSubarrays += evenCount;
                oddCount++;
            } else {
                totalSubarrays += oddCount;
                evenCount++;
            }
        }

        return (int) (totalSubarrays % MOD);
    }
}
