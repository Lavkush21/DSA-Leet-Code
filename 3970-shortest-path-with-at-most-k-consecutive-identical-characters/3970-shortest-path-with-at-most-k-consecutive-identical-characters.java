import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentWeight = current[0];
            int u = current[1];
            int streak = current[2];

            if (currentWeight > dist[u][streak]) {
                continue;
            }

            if (u == n - 1) {
                return currentWeight;
            }

            for (int[] neighborEdge : graph[u]) {
                int v = neighborEdge[0];
                int weight = neighborEdge[1];

                int nextStreak = (labels.charAt(u) == labels.charAt(v)) ? streak + 1 : 1;

                if (nextStreak > k) {
                    continue;
                }

                if (currentWeight + weight < dist[v][nextStreak]) {
                    dist[v][nextStreak] = currentWeight + weight;
                    pq.offer(new int[]{dist[v][nextStreak], v, nextStreak});
                }
            }
        }

        return -1;
    }
}
