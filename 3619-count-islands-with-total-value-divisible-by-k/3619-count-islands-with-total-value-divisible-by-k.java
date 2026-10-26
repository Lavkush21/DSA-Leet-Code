class Solution {
    private int m;
    private int n;
    private int[][] grid;
    
    // 4 directions: Up, Right, Down, Left
    private final int[] dirs = {-1, 0, 1, 0, -1}; 

    public int countIslands(int[][] grid, int k) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        this.m = grid.length;
        this.n = grid[0].length; // Fixed: Column size corrected
        this.grid = grid;
        int ans = 0;

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // Agar land cell mile (> 0) toh DFS chalayein
                if (grid[i][j] > 0) {
                    // Fixed: sum ko long mein calculate kar rahe hain integer overflow se bachne ke liye
                    if (dfs(i, j) % k == 0) { 
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private long dfs(int i, int j) {
        long sum = grid[i][j];
        grid[i][j] = 0; // Cell ko visited mark karne ke liye water (0) bana diya

        // 4 directions mein travel karein
        for (int d = 0; d < 4; ++d) {
            int x = i + dirs[d];
            int y = j + dirs[d + 1];
            
            // Boundary checking aur check karna ki aage land hai ya nahi
            if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] > 0) {
                sum += dfs(x, y);
            }
        }
        return sum;
    }
}
