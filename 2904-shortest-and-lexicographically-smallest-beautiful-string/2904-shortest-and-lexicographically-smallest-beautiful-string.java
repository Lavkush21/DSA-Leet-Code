class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int minLength = n + 1;
        String result = "";
        int countOnes = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }

         
            while (countOnes == k) {
                int currentLength = right - left + 1;
                String currentSubstring = s.substring(left, right + 1);

                if (currentLength < minLength) {
                    minLength = currentLength;
                    result = currentSubstring;
                } else if (currentLength == minLength && currentSubstring.compareTo(result) < 0) {
                    result = currentSubstring;
                }

                // Shrink the window
                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }

        return result;
    }
}
