import java.util.HashSet;
import java.util.Set;

class Solution {
    /**
     * Finds the length of the smallest positive integer n consisting of only the digit 1
     * that is divisible by k.
     *
     * @param k The given positive integer k.
     * @return The length of the smallest such integer n, or -1 if no such integer exists.
     */
    public int smallestRepunitDivByK(int k) {
        // If k is divisible by 2 or 5, no number consisting only of ones
        // can be divisible by k.
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        int remainder = 0;
        int length = 1;
        // Use a set to keep track of seen remainders modulo k.
        Set<Integer> seenRemainders = new HashSet<>();

        while (true) {
            // Calculate the remainder of the current number made of ones.
            // Formula: (previous_remainder * 10 + 1) % k
            remainder = (remainder * 10 + 1) % k;

            if (remainder == 0) {
                // If remainder is 0, we found the length of the number.
                return length;
            }

            if (!seenRemainders.add(remainder)) {
                // If the remainder has been seen before, we are in a cycle
                // and 0 will never be reached.
                return -1;
            }

            length++;
        }
    }
}
