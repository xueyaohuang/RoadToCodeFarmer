// Time O(ELogE), E is number of edges. 
// we offer each edge into queue once and then poll it out once.

// Hi, I am wondering how do you make sure there is no dead end since you always choose the "smallest" arrivals (min heap).
// 总是在当前from全部完成后，才加入res，相当于拓扑排序

class Solution {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (String[] pair : tickets) {
            String from = pair[0];
            String to = pair[1];
            graph.putIfAbsent(from, new PriorityQueue<>());
            graph.get(from).add(to);
        }
        LinkedList<String> res = new LinkedList<>();
        dfs(graph, "JFK", res);
        return res;
    }
    
    private void dfs(Map<String, PriorityQueue<String>> graph, String from, LinkedList<String> res) {
        PriorityQueue<String> pq = graph.get(from);
        while (pq != null && !pq.isEmpty()) {
            dfs(graph, pq.poll(), res);
        }
        res.addFirst(from);
    }
}
