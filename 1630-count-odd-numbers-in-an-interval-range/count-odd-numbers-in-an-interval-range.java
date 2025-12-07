public class Solution {
    public int countOdds(int low, int high) {
        // Mathematical approach: count = (high - low) / 2 + adjustment
        // If either low or high is odd, we need to add 1
        return (high - low) / 2 + ((low % 2 == 1 || high % 2 == 1) ? 1 : 0);
    }
    
    // Alternative simpler approach
    public int countOddsAlternative(int low, int high) {
        // Count odds from 0 to high minus count odds from 0 to low-1
        return countOddsFromZero(high) - countOddsFromZero(low - 1);
    }
    
    private int countOddsFromZero(int n) {
        // Number of odds from 0 to n (inclusive)
        return (n + 1) / 2;
    }
    
    // Test cases
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test case 1: low = 3, high = 7
        // Odd numbers: 3, 5, 7 → count = 3
        System.out.println(sol.countOdds(3, 7));  // Output: 3
        
        // Test case 2: low = 8, high = 10
        // Odd numbers: 9 → count = 1
        System.out.println(sol.countOdds(8, 10)); // Output: 1
        
        // Test case 3: low = 1, high = 1
        // Odd numbers: 1 → count = 1
        System.out.println(sol.countOdds(1, 1));  // Output: 1
        
        // Test case 4: low = 4, high = 8
        // Odd numbers: 5, 7 → count = 2
        System.out.println(sol.countOdds(4, 8));  // Output: 2
        
        // Test case 5: low = 0, high = 0
        // Odd numbers: none → count = 0
        System.out.println(sol.countOdds(0, 0));  // Output: 0
    }
}