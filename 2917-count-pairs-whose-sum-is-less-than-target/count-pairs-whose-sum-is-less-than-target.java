class Solution {
    public int countPairs(List<Integer> nums, int target) {
        // Sort the input list to enable the two-pointer approach.
        Collections.sort(nums); 

        int i = 0; // Pointer starting from the beginning of the list.
        int j = nums.size() - 1; // Pointer starting from the end of the list.
        int count = 0; // Variable to store the count of valid pairs.

        // Iterate while the left pointer is less than the right pointer.
        while (i < j) {
            int sum = nums.get(i) + nums.get(j); // Calculate the sum of elements at the current pointers.

            // If the sum is less than the target, all pairs formed with nums[i] and elements
            // between i and j (inclusive) will also have a sum less than the target.
            if (sum < target) {
                // Add the number of such pairs (j - i) to the count.
                count = count + (j - i); 
                i++; // Move the left pointer to the right to explore new pairs with a potentially larger sum.
            } else {
                // If the sum is greater than or equal to the target,
                // the current pair (nums[i], nums[j]) is not valid.
                // Move the right pointer to the left to try a smaller sum.
                j--; 
            }
        }
        return count; // Return the total count of pairs whose sum is less than the target.
    }
}