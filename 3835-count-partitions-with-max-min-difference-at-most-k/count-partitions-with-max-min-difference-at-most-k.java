import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        long[] dp = new long[n + 1];
        dp[0] = 1; // Base case: one way to partition an empty prefix (the start)
        int MOD = 1000000007;

        Deque<Integer> minq = new ArrayDeque<>(); // Monotonic increasing queue for minimums
        Deque<Integer> maxq = new ArrayDeque<>(); // Monotonic decreasing queue for maximums
        int left = 0; // Left boundary of the valid sliding window

        for (int right = 0; right < n; right++) {
            // Maintain monotonic decreasing max queue
            while (!maxq.isEmpty() && nums[right] > nums[maxq.peekLast()]) {
                maxq.pollLast();
            }
            maxq.addLast(right);

            // Maintain monotonic increasing min queue
            while (!minq.isEmpty() && nums[right] < nums[minq.peekLast()]) {
                minq.pollLast();
            }
            minq.addLast(right);

            // Shrink window from the left if the max-min difference exceeds k
            while (nums[maxq.peekFirst()] - nums[minq.peekFirst()] > k) {
                if (maxq.peekFirst() == left) {
                    maxq.pollFirst();
                }
                if (minq.peekFirst() == left) {
                    minq.pollFirst();
                }
                left++;
            }

            // All segments ending at 'right' starting from indices [left...right] are valid.
            // The number of ways to partition up to index 'right' is the sum of ways 
            // to partition up to each valid 'start - 1' index.
            // This requires an optimized sum (e.g., prefix sums or a Fenwick tree/Segment tree if constraints were different),
            // but for typical n <= 5 * 10^4, the simple sliding window with prefix sum array
            // optimization works.
            
            // NOTE: A direct sum in a loop from 'left' to 'right' in each iteration will be O(N^2) overall.
            // The efficient solution uses an optimized way to sum the dp values in the window [left-1, right-1]
            // as new valid partitions are formed.
            // The optimal approach uses an auxiliary data structure to manage prefix sums efficiently.
            
            // A simplified O(N^2) approach for understanding DP relation:
            // for (int j = left; j <= right; j++) {
            //     dp[right + 1] = (dp[right + 1] + dp[j]) % MOD;
            // }

            // To achieve O(N), we can maintain a running sum within the valid window:
            // We need a data structure that efficiently adds/removes elements from a range
            // or an optimized prefix sum structure. A standard prefix sum array will work
            // if we track the sum dynamically.
        }
        
        // The optimal O(N) approach uses prefix sums along with the sliding window logic. 
        // This direct implementation is O(N^2) for the inner loop. 
        // A full O(N) implementation requires more complex data structures (like a Fenwick tree or a custom sliding sum manager)
        // which might be an overcomplication for some interview settings.
        
        // Let's refine the DP state to use prefix sums:
        // P[i] = dp[0] + ... + dp[i]
        // dp[j+1] = P[j] - P[left-1]

        long[] prefixSum = new long[n + 2]; // index 1 to n+1
        prefixSum[1] = 1; // P[0]

        // Re-implementing with proper prefix sum logic (this needs careful index management)
        // The standard approach maintains a running sum of valid dp[j] values.
        
        // This is a complex hard problem requiring a very specific technique. The O(N) solution 
        // manages the running sum dynamically with the sliding window.

        // Reverting to the logic that needs an auxiliary structure for sum in range [left, right-1]:
        // The following snippet uses standard DP + sliding window logic described in resources.

        // Let's use the auxiliary structure for dynamic prefix sum (Fenwick Tree/BIT)

        // The simplest *correct* solution structure relies on a dynamic sum maintenance.

        // Re-writing with Fenwick tree (Binary Indexed Tree) for O(N log N)
        // Or using the specific running sum optimization.

        // The running sum optimization looks like this:
        // We need another variable to store the sum of dp values in the current valid range [left, right-1].

        long[] ways = new long[n + 1];
        ways[0] = 1;
        long sumWays = 1; // sum of valid ways so far (ways[0])
        int l = 0;
        Deque<Integer> minQ = new ArrayDeque<>();
        Deque<Integer> maxQ = new ArrayDeque<>();

        for (int r = 0; r < n; r++) {
            while (!maxQ.isEmpty() && nums[r] >= nums[maxQ.peekLast()]) maxQ.pollLast();
            maxQ.addLast(r);
            while (!minQ.isEmpty() && nums[r] <= nums[minQ.peekLast()]) minQ.pollLast();
            minQ.addLast(r);

            while (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > k) {
                if (maxQ.peekFirst() == l) maxQ.pollFirst();
                if (minQ.peekFirst() == l) minQ.pollFirst();
                // When 'l' moves, subtract ways[l] from sumWays
                sumWays = (sumWays - ways[l] + MOD) % MOD; 
                l++;
            }

            // The number of ways to end at index r is the sum of all ways[j] for j >= l
            ways[r + 1] = sumWays;
            // Add ways[r+1] to sumWays for the next iteration (this is wrong, sumWays should only sum valid start points)
            // sumWays += ways[r+1]; // No, this adds ways for partitions ending at r, which are start points for r+1
        }
        
        // Oh, the sumWays should track the sum of dp[j] for valid start points.
        // Let's use the code structure that tracks running sum correctly:

        long[] dp_optimal = new long[n + 1];
        dp_optimal[0] = 1;
        long runningSum = 1;
        int left_opt = 0;
        Deque<Integer> minDeque = new ArrayDeque<>();
        Deque<Integer> maxDeque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Update monotonic deques
            while (!maxDeque.isEmpty() && nums[i] >= nums[maxDeque.peekLast()]) maxDeque.pollLast();
            maxDeque.addLast(i);
            while (!minDeque.isEmpty() && nums[i] <= nums[minDeque.peekLast()]) minDeque.pollLast();
            minDeque.addLast(i);

            // Shrink the window from the left
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {
                if (maxDeque.peekFirst() == left_opt) maxDeque.pollFirst();
                if (minDeque.peekFirst() == left_opt) minDeque.pollFirst();
                runningSum = (runningSum - dp_optimal[left_opt] + MOD) % MOD;
                left_opt++;
            }

            // dp_optimal[i+1] is the sum of dp_optimal[j] for all valid j (left_opt <= j <= i)
            dp_optimal[i + 1] = runningSum; // Current runningSum holds the correct value
            
            // Add dp_optimal[i+1] to runningSum for the next iteration's calculation
            runningSum = (runningSum + dp_optimal[i + 1]) % MOD;
        }

        return (int) dp_optimal[n];
    }
}
