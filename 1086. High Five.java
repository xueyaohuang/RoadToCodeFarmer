class Solution {
    public int[][] highFive(int[][] items) {
        // 也可以用treemap，最后不用sort res了
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] item : items) {
            int id = item[0], score = item[1];
            map.putIfAbsent(id, new PriorityQueue<>());
            map.get(id).offer(score);
            if (map.get(id).size() > 5) {
                map.get(id).poll();
            }
        }
        int[][] res = new int[map.size()][2];
        int idx = 0;
        for (int id : map.keySet()) {
            int sum = 0;
            PriorityQueue<Integer> scores = map.get(id);
            for (int i = 0; i < 5; i++) {
                sum += scores.poll();
            }
            res[idx++] = new int[]{id, sum / 5};
        }
        Arrays.sort(res, (a, b) -> a[0] - b[0]);
        return res;
    }
}
