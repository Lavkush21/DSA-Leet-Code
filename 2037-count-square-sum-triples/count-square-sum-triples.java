class Solution {
    public int countTriples(int n) {
        int count = 0;
        
        // Iterate through all possible pairs (a, b)
        for (int a = 1; a <= n; a++) {
            for (int b = a; b <= n; b++) {  // Start from a to avoid duplicates
                int cSquared = a * a + b * b;
                
                // Check if cSquared is a perfect square
                int c = (int) Math.sqrt(cSquared);
                
                // Verify it's a perfect square and c is within bounds
                if (c * c == cSquared && c <= n) {
                    // If a == b, count once; otherwise count twice
                    if (a == b) {
                        count++;
                    } else {
                        count += 2;
                    }
                }
            }
        }
        
        return count;
    }
}