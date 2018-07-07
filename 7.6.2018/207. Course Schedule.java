// DFS detect cycle solution.
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[numCourses];
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adj[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(adj, i, visited)) {
                return false;
            }
        }
        return true;
    }
    private boolean hasCycle(List<Integer>[] adj, int i, int[] visited) {
        if (visited[i] == 1) {
            return true;
        }
        if (visited[i] == 2) {
            return false;
        }
        visited[i] = 1;
        for (int j = 0; j < adj[i].size(); j++) {
            if (hasCycle(adj, adj[i].get(j), visited)) {
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
