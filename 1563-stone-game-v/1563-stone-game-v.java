class Solution {
    private int[] pref;
    private int[][] memo;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        
        // Prefix sum array to compute subarray sums in O(1) time
        pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + stoneValue[i];
        }

        // Initialize memoization table with -1 (unvisited states)
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(memo[i], -1);
        }

        return dp(0, n - 1);
    }

    private int dp(int i, int j) {
        // Base case: Only 1 stone left, no points can be gained
        if (i == j) {
            return 0;
        }

        // Return cached result if already calculated
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int maxScore = 0;

        // Try every possible split point k between index i and j-1
        for (int k = i; k < j; k++) {
            int leftSum = pref[k + 1] - pref[i];
            int rightSum = pref[j + 1] - pref[k + 1];

            int currentScore = 0;
            if (leftSum < rightSum) {
                // Bob keeps the smaller left partition
                currentScore = leftSum + dp(i, k);
            } else if (leftSum > rightSum) {
                // Bob keeps the smaller right partition
                currentScore = rightSum + dp(k + 1, j);
            } else {
                // Sums are equal: Alice picks the side maximizing her total future score
                currentScore = leftSum + Math.max(dp(i, k), dp(k + 1, j));
            }

            maxScore = Math.max(maxScore, currentScore);
        }

        return memo[i][j] = maxScore;
    }
}
