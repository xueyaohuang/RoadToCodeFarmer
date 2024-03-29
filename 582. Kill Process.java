class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        // 构建adj list的graph
        for (int i = 0; i < pid.size(); i++) {
            adj.putIfAbsent(ppid.get(i), new ArrayList<>());
            adj.get(ppid.get(i)).add(pid.get(i));
        }
        dfs(adj, res, kill);
        return res;
    }
    
    private void dfs(Map<Integer, List<Integer>> adj, List<Integer> res, int cur) {
        res.add(cur);
        if (adj.containsKey(cur)) {
            for (int next : adj.get(cur)) {
                dfs(adj, res, next);
            }
        }
    }
}

class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        // 构建adj list的graph
        for (int i = 0; i < pid.size(); i++) {
            adj.putIfAbsent(ppid.get(i), new ArrayList<>());
            adj.get(ppid.get(i)).add(pid.get(i));
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(kill);
        res.add(kill);
        while (!queue.isEmpty()) {
        	int parent = queue.poll();
        	if (adj.containsKey(parent)) {
        		queue.addAll(adj.get(parent));
        		res.addAll(adj.get(parent));
        	}
        }
        return res;
    }
}
