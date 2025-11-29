class Solution {
    public int minOperations(int[] nums, int k) {
        // Use a long to store the sum in case of integer overflow for very large arrays/values
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // The minimum operations needed is the remainder when the sum is divided by k
        return (int) (sum % k);
    }
}
