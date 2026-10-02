import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        backtrack(n, n, current, result);
        return result;
    }

    private void backtrack(int openRemaining, int closeRemaining, StringBuilder current, List<String> result) {
        // Base case: If no parentheses remain to be added, a valid sequence is formed
        if (openRemaining == 0 && closeRemaining == 0) {
            result.add(current.toString());
            return;
        }

        // We can add an open parenthesis if there are any left
        if (openRemaining > 0) {
            current.append('(');
            backtrack(openRemaining - 1, closeRemaining, current, result);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // We can only add a close parenthesis if it won't exceed the matching open ones
        if (closeRemaining > openRemaining) {
            current.append(')');
            backtrack(openRemaining, closeRemaining - 1, current, result);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}
