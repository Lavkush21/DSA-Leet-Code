import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int curr = 0; // Stores the running remainder modulo 5

        for (int num : nums) {
            // Update the current remainder using the modular arithmetic property
            curr = (curr * 2 + num) % 5;
            
            // Check if the current remainder is 0 (divisible by 5)
            if (curr == 0) {
                ans.add(true);
            } else {
                ans.add(false);
            }
            // Alternatively: ans.add(curr % 5 == 0);
        }

        return ans;
    }
}
