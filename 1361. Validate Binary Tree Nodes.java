class Solution {
    // check cases:
    // 1. every node has indegree at most 1 (using set to check)
    // 2. number of edges equals n - 1 (count numEdges)
    // 3. no cycle (dfs detect cycle in directed graph)
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int numEdges = 0;
        Set<Integer> set = new HashSet<>();
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) {
                if (!set.add(leftChild[i])) {
                    return false;
                }
                adj[i].add(leftChild[i]);
                numEdges++;
            }
            if (rightChild[i] != -1) {
                if (!set.add(rightChild[i])) {
                    return false;
                }
                adj[i].add(rightChild[i]);
                numEdges++;
            }
        }
        if (numEdges != n - 1) {
            return false;
        }
        int[] visited = new int[n];
        // root can be any node
        for (int i = 0; i < n; i++) {
            if (hasCycle(adj, visited, i)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean hasCycle(List<Integer>[] adj, int[] visited, int i) {
        if (visited[i] == 2) {
            return false;
        }
        if (visited[i] == 1) {
            return true;
        }
        visited[i] = 1;
        for (int j : adj[i]) {
            if (hasCycle(adj, visited, j)) {
                return true;
            }
        }
        visited[i] = 2;
        return false;
    }
}
