import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long BASE1 = 1_000_003L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 1_000_033L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long OFFSET = 1_000_000_019L; // Handles negative/large values cleanly

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] p1 = new long[n + 1];
        long[] p2 = new long[n + 1];
        long[] h1 = new long[n + 1];
        long[] h2 = new long[n + 1];

        p1[0] = 1;
        p2[0] = 1;

        for (int i = 0; i < n; i++) {
            long val = nums[i] + OFFSET;
            p1[i + 1] = (p1[i] * BASE1) % MOD1;
            p2[i + 1] = (p2[i] * BASE2) % MOD2;

            h1[i + 1] = (h1[i] * BASE1 + val) % MOD1;
            h2[i + 1] = (h2[i] * BASE2 + val) % MOD2;
        }

        int low = 1, high = n, ans = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueOfLength(mid, n, h1, h2, p1, p2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueOfLength(int len, int n, long[] h1, long[] h2, long[] p1, long[] p2) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int i = 0; i + len <= n; i++) {
            int j = i + len;
            long hash1 = (h1[j] - (h1[i] * p1[len]) % MOD1 + MOD1) % MOD1;
            long hash2 = (h2[j] - (h2[i] * p2[len]) % MOD2 + MOD2) % MOD2;
            long combined = (hash1 << 32) ^ (hash2 & 0xffffffffL);

            freq.put(combined, freq.getOrDefault(combined, 0) + 1);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}
