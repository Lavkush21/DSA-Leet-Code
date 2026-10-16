import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[] treeSum;
    private long[] treeSqSum;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        treeSum = new long[4 * n];
        treeSqSum = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int j = 0; j < n; j++) {
            int prevIdx = lastSeen.getOrDefault(nums[j], -1);
            
            // Update the range [prevIdx + 1, j] with a value of +1
            update(0, 0, n - 1, prevIdx + 1, j, 1);
            
            // The root node treeSqSum[0] holds the total squared sum for all subarrays ending at index j
            totalSum = (totalSum + treeSqSum[0]) % MOD;
            
            lastSeen.put(nums[j], j);
        }

        return (int) totalSum;
    }

    private void pushDown(int node, int start, int end) {
        if (lazy[node] == 0) return;

        int mid = start + (end - start) / 2;
        int leftNode = 2 * node + 1;
        int rightNode = 2 * node + 2;

        updateLazyNode(leftNode, start, mid, lazy[node]);
        updateLazyNode(rightNode, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }

    private void updateLazyNode(int node, int start, int end, long val) {
        long len = end - start + 1;
        
        // Identity: (x + val)^2 = x^2 + 2*val*x + val^2 * len
        treeSqSum[node] = (treeSqSum[node] + 2 * val * treeSum[node] % MOD + (val * val % MOD) * len % MOD) % MOD;
        treeSum[node] = (treeSum[node] + val * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (r < start || end < l) return;

        if (l <= start && end <= r) {
            updateLazyNode(node, start, end, val);
            return;
        }

        pushDown(node, start, end);
        int mid = start + (end - start) / 2;

        update(2 * node + 1, start, mid, l, r, val);
        update(2 * node + 2, mid + 1, end, l, r, val);

        treeSum[node] = (treeSum[2 * node + 1] + treeSum[2 * node + 2]) % MOD;
        treeSqSum[node] = (treeSqSum[2 * node + 1] + treeSqSum[2 * node + 2]) % MOD;
    }
}
