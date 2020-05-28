class Solution {
    public boolean possibleBipartition(int N, int[][] dislikes) {
        if (dislikes == null || dislikes.length == 0) {
            return true;
        }
        List<Integer>[] adj = new ArrayList[N + 1];
        int[] color = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] dis : dislikes) {
            adj[dis[0]].add(dis[1]);
            adj[dis[1]].add(dis[0]);
        }
        for (int i = 1; i <= N; i++) {
            if (color[i] == 0) {
                if (hasOddCycle(adj, i, 1, color)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean hasOddCycle(List<Integer>[] adj, int i, int curColor, int[] color) {
        if (color[i] != 0) {
            return color[i] != curColor;
        }
        color[i] = curColor;
        for (int j : adj[i]) {
            if (hasOddCycle(adj, j, -curColor, color)) {
                return true;
            }
        }
        return false;
    }
}
