class Solution {
    private int m;
    private int n;
    private int K;
    private static final int MOD = 1_000_000_007;
    // memoization table to store results of subproblems
    private Integer[][][] mem;

    public int numberOfPaths(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        K = k;
        mem = new Integer[m][n][K];
        return dp(grid, 0, 0, 0);
    }

    private int dp(int[][] grid, int i, int j, int currentSumMod) {
        // Base case: out of bounds
        if (i == m || j == n) {
            return 0;
        }

        // Calculate the new sum modulo k
        int newSumMod = (currentSumMod + grid[i][j]) % K;

        // Base case: reached the destination
        if (i == m - 1 && j == n - 1) {
            return newSumMod == 0 ? 1 : 0;
        }

        // Check if the result is already memoized
        if (mem[i][j][newSumMod] != null) {
            return mem[i][j][newSumMod];
        }

        // Recursive calls to move down and right
        int downPaths = dp(grid, i + 1, j, newSumMod);
        int rightPaths = dp(grid, i, j + 1, newSumMod);

        // Store the result in the memoization table and return
        return mem[i][j][newSumMod] = (downPaths + rightPaths) % MOD;
    }
}
