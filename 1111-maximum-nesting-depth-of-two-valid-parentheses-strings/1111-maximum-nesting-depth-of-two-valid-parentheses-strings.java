class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int depth = 0;

        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '(') {
                // Distribute based on the parity of the current depth
                ans[i] = depth % 2;
                depth++;
            } else {
                depth--;
                // Maintain the same parity for the matching closing parenthesis
                ans[i] = depth % 2;
            }
        }

        return ans;
    }
}
