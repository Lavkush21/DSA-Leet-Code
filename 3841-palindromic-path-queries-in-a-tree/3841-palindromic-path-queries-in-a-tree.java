import java.util.*;

class Solution {
    int n;
    List<Integer>[] adj;
    int[] tin, tout;
    int timer;
    int[][] up;
    int[] depth;
    int[] val;
    int[] bit;

    // Fenwick Tree (BIT) supporting Range Update and Point Query via XOR
    void bitUpdate(int idx, int delta) {
        for (; idx <= n; idx += idx & -idx) {
            bit[idx] ^= delta;
        }
    }

    void bitUpdateRange(int l, int r, int delta) {
        bitUpdate(l, delta);
        bitUpdate(r + 1, delta);
    }

    int bitQuery(int idx) {
        int res = 0;
        for (; idx > 0; idx -= idx & -idx) {
            res ^= bit[idx];
        }
        return res;
    }

    // DFS to establish Euler Tour timestamps and Binary Lifting table
    void dfs(int u, int p, int d) {
        tin[u] = ++timer;
        depth[u] = d;
        up[u][0] = p == -1 ? u : p;
        for (int i = 1; i < 18; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1);
            }
        }
        tout[u] = timer;
    }

    // Standard LCA using Binary Lifting
    int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = 17; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = 17; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[n][18];
        val = new int[n];
        bit = new int[n + 2];
        timer = 0;

        // Step 1: Precalculate tree structure
        dfs(0, -1, 0);

        // Step 2: Initialize Fenwick Tree with starting character masks
        for (int i = 0; i < n; i++) {
            val[i] = 1 << (s.charAt(i) - 'a');
            bitUpdateRange(tin[i], tout[i], val[i]);
        }

        List<Boolean> answer = new ArrayList<>();

        // Step 3: Process updates and path queries
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                
                int newVal = 1 << (c - 'a');
                int diff = val[u] ^ newVal;
                val[u] = newVal;
                
                // Update the prefix XOR of everything in u's subtree
                bitUpdateRange(tin[u], tout[u], diff);
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                
                int lca = getLCA(u, v);
                int mask = bitQuery(tin[u]) ^ bitQuery(tin[v]) ^ val[lca];
                
                // Rearrangeable into a palindrome if at most 1 bit is set
                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }
}
