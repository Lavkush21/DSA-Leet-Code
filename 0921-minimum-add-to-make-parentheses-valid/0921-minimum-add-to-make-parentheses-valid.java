class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int closeNeeded = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // An opening parenthesis means we'll eventually need a matching ')'
                closeNeeded++;
            } else {
                // If we see a ')' and have unmatched '(' available, pair them up
                if (closeNeeded > 0) {
                    closeNeeded--;
                } else {
                    // Otherwise, this ')' has no matching '(' before it, so we need to add a '('
                    openNeeded++;
                }
            }
        }
        
        // The total additions required is the sum of missing opening and closing brackets
        return openNeeded + closeNeeded;
    }
}
