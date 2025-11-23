public class Solution {
    public int maxSumDivThree(int[] nums) {
        // dp[r] = maximum sum seen so far whose remainder mod 3 is r
        int[] dp = new int[3];
        dp[0] = 0;
        dp[1] = dp[2] = Integer.MIN_VALUE / 2;  // to avoid overflow when adding

        for (int num : nums) {
            int[] newDp = dp.clone();  // snapshot of old dp
            for (int r = 0; r < 3; r++) {
                int newRem = (r + num % 3) % 3;
                newDp[newRem] = Math.max(newDp[newRem], dp[r] + num);
            }
            dp = newDp;
        }

        return dp[0];
    }
}
