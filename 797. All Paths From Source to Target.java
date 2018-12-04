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

// BFS
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(Arrays.asList(0));
        
        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();
            int curNode = cur.get(cur.size() - 1);
            if (curNode == n) {
                res.add(cur);
            }
            for (int next : graph[curNode]) {
                List<Integer> nextList = new ArrayList<>(cur);
                nextList.add(next);
                queue.offer(nextList);
            }
        }
        return res;
    }
}
