

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int countPalindromicSubsequence(String s) {
        int[] firstIndex = new int[26];
        int[] lastIndex = new int[26];
        // Initialize firstIndex with a value indicating not found yet (or a value greater than any valid index)
        Arrays.fill(firstIndex, Integer.MAX_VALUE);
        
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            // Update the first occurrence index
            firstIndex[index] = Math.min(firstIndex[index], i);
            // Always update the last occurrence index
            lastIndex[index] = i;
        }
        
        int count = 0;
        // Iterate through all 26 possible end characters
        for (int i = 0; i < 26; i++) {
            // Check if the character appeared at least twice
            if (firstIndex[i] < lastIndex[i]) {
                // Count the number of unique characters in the substring between the first and last occurrence
                Set<Character> uniqueChars = new HashSet<>();
                for (int j = firstIndex[i] + 1; j < lastIndex[i]; j++) {
                    uniqueChars.add(s.charAt(j));
                }
                count += uniqueChars.size();
            }
        }
        
        return count;
    }
}
