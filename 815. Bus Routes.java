class Solution {
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) {
            return 0;
        }
        // used:记录哪些bus已经乘坐过，检查哪些车坐过比检查哪些站到过快很多
        Set<Integer> used = new HashSet<>();
        // key: stop, value: 哪些bus可以到这个stop
        Map<Integer, List<Integer>> map = new HashMap<>();
        // queue里面的元素是stop
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j : routes[i]) {
                if (!map.containsKey(j)) {
                    map.put(j, new ArrayList<>());
                }
                map.get(j).add(i);
            }
        }
        queue.offer(S);
        int res = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int stop = queue.poll();
                for (int bus : map.get(stop)) {
                    if (!used.contains(bus)) {
                        used.add(bus);
                        for (int nextStop : routes[bus]) {
                            if (nextStop == T) {
                                return res;
                            }
                            queue.offer(nextStop);
                        }
                    }
                }
            }
            res++;
        }
        return -1;
    }
}
