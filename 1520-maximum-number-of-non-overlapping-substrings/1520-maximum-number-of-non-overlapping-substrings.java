


import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        
        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int charIdx = s.charAt(i) - 'a';
            if (first[charIdx] == -1) {
                first[charIdx] = i;
            }
            last[charIdx] = i;
        }
        
        List<int[]> validIntervals = new ArrayList<>();
        
        // Step 2: Expand intervals to satisfy the rule for all characters inside
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) continue;
            
            int start = first[i];
            int end = last[i];
            boolean isValid = true;
            
            for (int j = start; j <= end; j++) {
                int charIdx = s.charAt(j) - 'a';
                
                // If a character inside started before our current start,
                // this interval is redundant/invalid (will be handled by that character's start)
                if (first[charIdx] < start) {
                    isValid = false;
                    break;
                }
                // Extend the end to include all occurrences of the inner character
                end = Math.max(end, last[charIdx]);
            }
            
            if (isValid) {
                validIntervals.add(new int[]{start, end});
            }
        }
        
        // Step 3: Sort intervals by end index to pick greedily
        validIntervals.sort((a, b) -> Integer.compare(a[1], b[1]));
        
        List<String> result = new ArrayList<>();
        int prevEnd = -1;
        
        for (int[] interval : validIntervals) {
            int start = interval[0];
            int end = interval[1];
            
            // If it starts after the previous end, we found a non-overlapping substring
            if (start > prevEnd) {
                result.add(s.substring(start, end + 1));
                prevEnd = end;
            } 
            // If it is completely nested inside the previous substring, 
            // replace it to minimize total length.
            else if (start > validIntervals.get(validIntervals.indexOf(interval) - 1)[0] && end <= prevEnd) {
                result.set(result.size() - 1, s.substring(start, end + 1));
                prevEnd = end;
            }
        }
        
        return result;
    }
}
