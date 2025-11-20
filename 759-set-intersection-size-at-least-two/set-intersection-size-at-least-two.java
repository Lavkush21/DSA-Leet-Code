class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Sort: end asc, start desc
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) return b[0] - a[0];
            return a[1] - b[1];
        });

        int p1 = -1;  // smaller selected point
        int p2 = -1;  // larger selected point
        int count = 0;

        for (int[] it : intervals) {
            int l = it[0], r = it[1];

            // Case 1: both p1 and p2 inside interval
            if (p1 >= l && p2 >= l) {
                continue;
            }

            // Case 2: only p2 is inside interval
            if (p2 >= l) {
                count++;
                p1 = p2;
                p2 = r;
            } else {
                // Case 3: need two new points
                count += 2;
                p1 = r - 1;
                p2 = r;
            }
        }

        return count;
    }
}
