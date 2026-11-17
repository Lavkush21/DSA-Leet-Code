import java.util.Arrays;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000;
        
        int[][] cost = new int[n][n];
        for (int[] row : cost) {
            Arrays.fill(row, INF);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                for (int d = 1; d * d <= len; d++) {
                    if (len % d == 0) {
                        cost[i][j] = Math.min(cost[i][j], getCost(s, i, j, d));
                        if (d > 1 && d * d != len) {
                            cost[i][j] = Math.min(cost[i][j], getCost(s, i, j, len / d));
                        }
                    }
                }
            }
        }
        
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        
        for (int i = 2; i <= n; i++) {
            for (int p = 1; p <= k; p++) {
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[j][p - 1] != INF && cost[j][i - 1] != INF) {
                        dp[i][p] = Math.min(dp[i][p], dp[j][p - 1] + cost[j][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
    
    private int getCost(String s, int start, int end, int d) {
        int changes = 0;
        int len = end - start + 1;
        
        for (int rem = 0; rem < d; rem++) {
            int left = start + rem;
            int right = start + len - d + rem;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    changes++;
                }
                left += d;
                right -= d;
            }
        }
        return changes;
    }
}
