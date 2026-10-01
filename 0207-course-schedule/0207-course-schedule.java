import java.util.*;

class Solution { 
    public boolean canFinish(int numCourses, int[][] prerequisites) { 
        // 1. Initialize the adjacency list graph
        List<List<Integer>> graph = new ArrayList<>(); 
        for(int i = 0; i < numCourses; i++) { 
            graph.add(new ArrayList<>()); 
        } 
        
        // 2. Build the graph and calculate indegrees
        int[] indegree = new int[numCourses]; 
        for(int[] edge : prerequisites) { 
            int course = edge[0]; 
            int prerequisite = edge[1]; 
            graph.get(prerequisite).add(course); 
            indegree[course]++; 
        } 
        
        // 3. Add all courses with 0 prerequisites to the queue
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {  
            if(indegree[i] == 0) { 
                q.offer(i); 
            } 
        } 
        
        // 4. Process the graph
        int count = 0; 
        while(!q.isEmpty()) { 
            int course = q.poll(); 
            count++; 
            
            for(int child : graph.get(course)) { 
                indegree[child]--; 
                // Added missing logic: If indegree drops to 0, push it to queue
                if(indegree[child] == 0) {
                    q.offer(child);
                }
            } 
        } 
        
        // 5. If we processed all courses, there is no cycle
        return count == numCourses; 
    }
}
