import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Keep doubling original as long as it is found in the set
        while (numSet.contains(original)) {
            original *= 2;
        }

        return original;
    }
}
