import java.util.ArrayList;
import java.util.List;

class Solution {
    private int componentCount = 0;
    private List<List<Integer>> adj;
    private int[] values;
    private int k;

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.values = values;
        this.k = k;
        this.componentCount = 0;
        this.adj = new ArrayList<>();
        
        // 1. Build the adjacency list
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dfs(0, -1); 

        // 3. Return the total count of valid components found
        return componentCount;
    }
    private long dfs(int u, int parent) {
        // Start the current subtree sum with the node's own value
        long subTreeSum = values[u];

        // Recurse on all children
        for (int v : adj.get(u)) {
            if (v != parent) {
                // Add the sum returned by the child's subtree
                subTreeSum += dfs(v, u);
            }
        }
        if (subTreeSum % k == 0) {
            componentCount++;
           
            return 0; 
        }

        return subTreeSum;
    }
}
