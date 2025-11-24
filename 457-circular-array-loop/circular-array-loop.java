import java.util.HashSet;
import java.util.Set;

class Solution {
    // Helper function to calculate the next index in the circular array
    public int calcNextIdx(int[] nums, int curr) {
        int n = nums.length;
        // The modulo operator in Java can return negative results for negative inputs,
        // so we use (a % n + n) % n to ensure a positive result for the index.
        return (curr + nums[curr] % n + n) % n;
    }

    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }

            int slow = i;
            int fast = i;
            boolean isForward = nums[i] > 0; // Check the direction of the current sequence

            while (true) {
                // Move slow pointer by one step
                slow = calcNextIdx(nums, slow);
                if (isInvalid(nums, slow, isForward)) break;

                // Move fast pointer by two steps
                fast = calcNextIdx(nums, fast);
                if (isInvalid(nums, fast, isForward)) break;
                fast = calcNextIdx(nums, fast);
                if (isInvalid(nums, fast, isForward)) break;

                // If slow and fast pointers meet, a cycle is found
                if (slow == fast) {
                    // Check if the cycle length is greater than 1
                    if (slow == calcNextIdx(nums, slow)) {
                        // The loop has only one element (e.g., nums[i] % n == 0)
                        break;
                    }
                    return true; // Valid loop found
                }
            }

            // If a valid loop wasn't found starting from 'i',
            // mark visited elements as 0 to avoid redundant checks.
            slow = i;
            while (!isInvalid(nums, slow, isForward)) {
                int next = calcNextIdx(nums, slow);
                nums[slow] = 0;
                slow = next;
            }
        }

        return false;
    }

    // Helper function to check if the next step is invalid (changes direction)
    private boolean isInvalid(int[] nums, int curr, boolean isForward) {
        if (nums[curr] == 0) return true;
        // Check if the direction of movement is consistent
        if (isForward && nums[curr] < 0 || !isForward && nums[curr] > 0) {
            return true;
        }
        return false;
    }
}