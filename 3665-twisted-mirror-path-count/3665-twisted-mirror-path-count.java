class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1000000007;
        
        // R[i][j] tracks paths entering (i, j) moving right
        // D[i][j] tracks paths entering (i, j) moving down
        int[][] R = new int[m][n];
        int[][] D = new int[m][n];
        
        // Base case: Start at (0, 0)
        // We initialize R[0][0] = 1 and D[0][0] = 0 (or vice versa) so that 
        // the initial outward transitions from (0,0) each count exactly 1 option.
        R[0][0] = 1; 
        D[0][0] = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                
                // 1. Calculate paths coming from the left neighbor (moving right)
                if (j > 0) {
                    R[i][j] = D[i][j - 1];
                    if (grid[i][j - 1] == 0) {
                        R[i][j] = (R[i][j] + R[i][j - 1]) % MOD;
                    }
                }
                
                // 2. Calculate paths coming from the top neighbor (moving down)
                if (i > 0) {
                    D[i][j] = R[i - 1][j];
                    if (grid[i - 1][j] == 0) {
                        D[i][j] = (D[i][j] + D[i - 1][j]) % MOD;
                    }
                }
            }
        }
        
        // The answer is the total paths entering the destination from either direction
        return (R[m - 1][n - 1] + D[m - 1][n - 1]) % MOD;
    }
}
