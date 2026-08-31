/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // A list must have at least 3 nodes to have a local minima or maxima
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int lastCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int index = 1; // Keeping track of the 0-indexed position

        while (curr.next != null) {
            ListNode nextNode = curr.next;
            
            // Check if the current node is a local maxima or local minima
            boolean isMaxima = curr.val > prev.val && curr.val > nextNode.val;
            boolean isMinima = curr.val < prev.val && curr.val < nextNode.val;

            if (isMaxima || isMinima) {
                if (firstCriticalIndex == -1) {
                    // This is the very first critical point found
                    firstCriticalIndex = index;
                } else {
                    // Update the minimum distance between adjacent critical points
                    minDistance = Math.min(minDistance, index - lastCriticalIndex);
                }
                // Track the most recent critical point index
                lastCriticalIndex = index;
            }

            // Move pointers forward
            prev = curr;
            curr = nextNode;
            index++;
        }

        // If fewer than two critical points were found, return [-1, -1]
        if (firstCriticalIndex == lastCriticalIndex) {
            return new int[]{-1, -1};
        }

        // Max distance is always the distance between the first and the last critical point
        int maxDistance = lastCriticalIndex - firstCriticalIndex;

        return new int[]{minDistance, maxDistance};
    }
}
