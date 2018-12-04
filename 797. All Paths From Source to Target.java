class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        // 0是确定在结果里的，所以一开始就加进去，不需要backtracking的过程中加
        temp.add(0);
        backtrack(graph, res, temp, 0);
        return res;
    }
    
    private void backtrack(int[][] graph, List<List<Integer>> res, List<Integer> temp, int node) {
        // node实际上在上一层recursion加进去
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i : graph[node]) {
            temp.add(i);
            backtrack(graph, res, temp, i);
            temp.remove(temp.size() - 1);
        }
    }
}
