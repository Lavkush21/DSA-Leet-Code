import java.util.Stack;

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // Push -1 as a base boundary for valid substrings starting at index 0
        stack.push(-1); 
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                // Store the index of the open parenthesis
                stack.push(i);
            } else {
                // Pop the last matching base or open parenthesis index
                stack.pop();
                
                if (stack.isEmpty()) {
                    // If empty, this closing parenthesis has no match.
                    // It acts as the new baseline boundary for future substrings.
                    stack.push(i);
                } else {
                    // Calculate the length of the current valid substring
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
}
