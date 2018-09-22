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
