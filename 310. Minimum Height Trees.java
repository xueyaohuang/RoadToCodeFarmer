// key points:
// 1. the root of MHT is the middle point of the longest path in the tree; hence there are at most two MHT roots.
// 2. BFS from the bottom (leaves) to the top until the last level with <=2 nodes.
// 3. remove leaves 
// 像剥洋葱一样，从外层，一层一层去掉degree为1的node，直到剩下最后1个或者2个。
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return Arrays.asList(0);
        }
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] degree = new int[n];
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            degree[i] = adj[i].size();
            if (degree[i] == 1) {
                leaves.add(i);
            }
        }
        int count = n;
        while (count > 2) {
            count -= leaves.size();
            List<Integer> nextLeaves = new ArrayList<>();
            for (int i : leaves) {
                for (int j : adj[i]) {
                    degree[j]--;
                    if (degree[j] == 1) {
                        nextLeaves.add(j);
                    }
                }
            }
            leaves = nextLeaves;
        }
        return leaves;
    }
}
// Longest path in an undirected tree: https://www.geeksforgeeks.org/longest-path-undirected-tree/
