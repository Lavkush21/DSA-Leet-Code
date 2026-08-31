/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        } // Closed the if-statement bracket properly here

        // Recursively find the maximum path sum from left and right subtrees
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));

        // Update the global maximum path sum (handles the current node as a path apex)
        maxSum = Math.max(maxSum, left + right + root.val);

        // CRITICAL FIX: Return the maximum single-branch path sum to the parent node
        return root.val + Math.max(left, right);
    }
}
