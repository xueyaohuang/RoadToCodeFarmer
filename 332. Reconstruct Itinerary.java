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

public class Solution {
    private HashMap<String, List<String>> adjList = new HashMap<>();
    private LinkedList<String> route = new LinkedList<>();
    private int numTickets = 0;
    private int numTicketsUsed = 0;
    
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null || tickets.length == 0) return route;
        // build graph
        numTickets = tickets.length;
        for (int i = 0; i < tickets.length; ++i) {
            if (!adjList.containsKey(tickets[i][0])) {
                // create a new list
                List<String> list = new ArrayList<>();
                list.add(tickets[i][1]);
                adjList.put(tickets[i][0], list);
            } else {
                // add to existing list
                adjList.get(tickets[i][0]).add(tickets[i][1]);
            }
        }
        // sort vertices in the adjacency list so they appear in lexical order
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        // start DFS
        route.add("JFK");
        dfsRoute("JFK");
        return route;
    }
    
    private void dfsRoute(String v) {
        // base case: vertex v is not in adjacency list
        // v is not a starting point in any itinerary, or we would have stored it
        // thus we have reached end point in our DFS
        if (!adjList.containsKey(v)) return;
        List<String> list = adjList.get(v);
        for (int i = 0; i < list.size(); ++i) {
            String neighbor = list.get(i);
            // remove ticket(route) from graph
            list.remove(i);
            route.add(neighbor);
            numTicketsUsed++;
            dfsRoute(neighbor);
            // we only return when we have used all tickets
            if (numTickets == numTicketsUsed) return;
            // otherwise we need to revert the changes and try other tickets
            list.add(i, neighbor);
            // This line took me a long time to debug
            // we must remove the last airport, since in an itinerary, the same airport can appear many times!!
            route.removeLast();
            numTicketsUsed--;
        }
    }
    
}
