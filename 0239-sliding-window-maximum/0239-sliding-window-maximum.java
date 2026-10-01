import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || k == 0) return new int[0];
        
        int[] result = new int[n - k + 1];
        // ArrayDeque is faster than LinkedList for standard Queue/Deque operations
        Deque<Integer> deque = new ArrayDeque<>(); 
        
        for (int right = 0; right < n; right++) {
            // Remove indices that are out of the current window bounds
            while (!deque.isEmpty() && deque.peekFirst() <= right - k) {
                deque.pollFirst();
            }
            
            // Remove elements from the back that are smaller than the current element
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[right]) {
                deque.pollLast();
            }
            
            // Add the current element's index
            deque.addLast(right);
            
            // The window reaches size k starting from index k - 1
            if (right >= k - 1) {
                result[right - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
}
