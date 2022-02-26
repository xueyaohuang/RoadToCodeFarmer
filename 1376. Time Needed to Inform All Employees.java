class Solution {
    int time = 0;
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            int m = manager[i];
            if (m != -1) {
                adj[m].add(i);
            }
        }
        dfs(headID, adj, 0, informTime);
        return time;
    }
    
    private void dfs(int employee, List<Integer>[] adj, int curTime, int[] informTime) {
        time = Math.max(time, curTime);
        for (int i : adj[employee]) {
            dfs(i, adj, curTime + informTime[employee], informTime);
        }
    }
}
