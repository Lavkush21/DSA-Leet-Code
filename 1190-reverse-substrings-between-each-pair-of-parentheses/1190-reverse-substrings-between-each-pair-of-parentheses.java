import java.util.Stack;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Stack<Integer> openParenthesesIndices = new Stack<>();
        
        // Step 1: Pair up the opening and closing parentheses
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                openParenthesesIndices.push(i);
            } else if (s.charAt(i) == ')') {
                int j = openParenthesesIndices.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        
        // Step 2: Traverse and build the string with direction changes
        StringBuilder result = new StringBuilder();
        int direction = 1; // 1 means moving right, -1 means moving left
        
        for (int i = 0; i < n; i += direction) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];          // Teleport to the matching bracket
                direction = -direction; // Reverse the direction
            } else {
                result.append(s.charAt(i));
            }
        }
        
        return result.toString();
    }
}
