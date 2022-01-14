// BFS
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for (int[] flight : flights) {
            adj[flight[0]].add(new int[]{flight[1], flight[2]});
        }
        int stops = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);
        while (!queue.isEmpty()) {
            if (stops > k) {
                break;
            }
            int size = queue.size();
            int[] temp = Arrays.copyOf(distance, distance.length);
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int[] e : adj[cur]) {
                    int minCost = distance[cur] + e[1];
                    if (minCost < temp[e[0]]) {
                        temp[e[0]] = minCost;
                        queue.offer(e[0]);
                    }
                }
            }
            distance = temp;
            stops++;
        }
        return distance[dst] == Integer.MAX_VALUE ? -1 : distance[dst];
    }
}

// pq, TLE now
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
        for (int[] f : flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
            prices.get(f[0]).put(f[1], f[2]);
        }
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
        pq.add(new int[] {0, src, k + 1});
        while (!pq.isEmpty()) {
            int[] top = pq.remove();
            int price = top[0];
            int city = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops > 0) {
                Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                for (int a : adj.keySet()) {
                    pq.add(new int[] {price + adj.get(a), a, stops - 1});
                }
            }
        }
        return -1;
    }
}

// Dijkstra
class Solution {
    private class City implements Comparable<City>{
        int id;
        int costFromSrc;
        int stopFromSrc;
        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
        public boolean equals(City c){
            if(c instanceof City)
                return this.id == c.id;
            return false;
        }
        public int compareTo(City c){
            return this.costFromSrc - c.costFromSrc;
        }
    }
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for(int i = 0; i < flights.length; i++)
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2]; 
						
        PriorityQueue<City> minHeap = new PriorityQueue();
        minHeap.offer(new City(src,0,0));
				
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;
				
        while (!minHeap.isEmpty()) {
            City curCity = minHeap.poll();
            if (curCity.id == dst) return curCity.costFromSrc;
            if (curCity.stopFromSrc == K + 1) continue; 
            int[] nexts = srcToDst[curCity.id];
            
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }
        
        return -1;
    }
}


// bellman-ford
class Solution {
    
    // Bellman-Ford.
    // Video : https://www.youtube.com/watch?v=9PHkk0UavIM
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] dist = new int[n];  // n is the number of vertices.
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        int[] dist2;
        for (int k = 0; k < K + 1; k++) {
            // 为了不pollute上一个iteration的结果，否则有bug!
            dist2 = Arrays.copyOf(dist, dist.length);
            
            for (int[] edge : flights) {
                int u = edge[0];
                if (dist[u] == Integer.MAX_VALUE) {
                    continue;
                }
                int v = edge[1], cost = edge[2];
                dist2[v] = Math.min(dist2[v], dist[u] + cost);
            }
            
            dist = dist2;
        }
        
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
