import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Pass 1: Find the closest matching ancestor for each node
        reassignParents(0, origTree, s, lastSeen, newParent);

        // Build the updated tree
        List<Integer>[] newTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newTree[newParent[i]].add(i);
        }

        // Pass 2: Calculate subtree sizes in the modified tree
        int[] ans = new int[n];
        computeSizes(0, newTree, ans);
        return ans;
    }

    private void reassignParents(int u, List<Integer>[] tree, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        lastSeen[charIdx] = u;
        for (int v : tree[u]) {
            reassignParents(v, tree, s, lastSeen, newParent);
        }
        // Backtrack so sibling branches don't see u as an ancestor
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u, List<Integer>[] tree, int[] ans) {
        int size = 1;
        for (int v : tree[u]) {
            size += computeSizes(v, tree, ans);
        }
        ans[u] = size;
        return size;
    }
}
