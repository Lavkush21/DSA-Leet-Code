import java.util.HashMap;

class Solution { 
    public int totalFruit(int[] fruits) { 
        int left = 0; 
        int ans = 0; 
        HashMap<Integer, Integer> map = new HashMap<>(); 
        
        for (int right = 0; right < fruits.length; right++) { 
            // Add the current fruit to our window
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1); 
            
            // Shrink the window from the left if we have more than 2 types of fruit
            while (map.size() > 2) { 
                map.put(fruits[left], map.get(fruits[left]) - 1); 
                
                // Only remove the fruit type from the map if its count reaches 0
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]); 
                }
                left++; // Move the left pointer forward to shrink the window
            } 
            
            // Calculate the maximum number of fruits we can collect so far
            ans = Math.max(ans, right - left + 1); 
        } 
        
        return ans; 
    } 
}
