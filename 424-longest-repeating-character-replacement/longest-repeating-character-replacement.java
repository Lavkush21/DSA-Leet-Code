import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int characterReplacement(String s, int k) {
        // Frequency map for characters in the current window
        Map<Character, Integer> counts = new HashMap<>();
        int left = 0;
        int maxFreq = 0;
        int maxLength = 0;
        int n = s.length();

        // Iterate through the string with the right pointer
        for (int right = 0; right < n; right++) {
            char rightChar = s.charAt(right);
            counts.put(rightChar, counts.getOrDefault(rightChar, 0) + 1);
            
          
            maxFreq = Math.max(maxFreq, counts.get(rightChar));
            
            // Check if the current window is invalid.
            // Window size = right - left + 1
            // Replacements needed = Window size - maxFreq
            if ((right - left + 1) - maxFreq > k) {
                // Shrink the window from the left
                char leftChar = s.charAt(left);
                counts.put(leftChar, counts.get(leftChar) - 1);
                left++;
            }
            
            // The current window size is a potential candidate for the answer
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
