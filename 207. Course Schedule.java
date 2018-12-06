// DFS detect cycle solution.
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] visited = new int[numCourses];
        List<Integer>[] adjList = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adjList[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (hasCycle(adjList, i, visited)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean hasCycle(List<Integer>[] adjList, int i, int[] visited) {
        
        if (visited[i] == 1) {
            return true;
        }
        if (visited[i] == 2) {
            return false;
        }
        visited[i] = 1;
        for (int j = 0; j < adjList[i].size(); j++) {
            if (hasCycle(adjList, adjList[i].get(j), visited)) {
                return true;
            }
        }
        visited[i] = 2;
        return false;
    }
}

// BFS
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        Queue<Integer> finished = new LinkedList<>();
        int canFinish = 0;
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
            inDegree[prerequisites[i][0]]++;
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                finished.offer(i);
            }
        }
        while (!finished.isEmpty()) {
            int curCourse = finished.poll();
            canFinish++;
            for (int i = 0; i < adj[curCourse].size(); i++) {
                inDegree[adj[curCourse].get(i)]--;
                if (inDegree[adj[curCourse].get(i)] == 0) {
                    finished.offer(adj[curCourse].get(i));
                }
            }
        }
        return canFinish == numCourses;
    }
}
