class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;

        // Pruning optimizations:
        // 1. Total path length (m + n - 1) must be even to have balanced pairs.
        // 2. Start character cannot be a closing parenthesis.
        // 3. End character cannot be an opening parenthesis.
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        // Maximum possible open parenthesis count can't exceed (m + n)
        this.visited = new boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int openCount) {
        // Handle parenthesis balance tracking
        if (grid[r][c] == '(') {
            openCount++;
        } else {
            openCount--;
        }

        // If openCount drops below 0, it means a closing brace appeared without an opening one
        // If openCount exceeds remaining moves, it's impossible to balance it back to 0
        if (openCount < 0 || openCount > (m - r + n - c)) {
            return false;
        }

        // Base case: Reached destination
        if (r == m - 1 && c == n - 1) {
            return openCount == 0;
        }

        // If this state has been visited and failed, stop exploring
        if (visited[r][c][openCount]) {
            return false;
        }
        visited[r][c][openCount] = true;

        // Move Down
        if (r + 1 < m && dfs(r + 1, c, openCount)) {
            return true;
        }

        // Move Right
        if (c + 1 < n && dfs(r, c + 1, openCount)) {
            return true;
        }

        return false;
    }
}
