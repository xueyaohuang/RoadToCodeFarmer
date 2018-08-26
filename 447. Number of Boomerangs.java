class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        // points[i]是Boomerangs的中点
        for (int i = 0; i < points.length; i++) {
            map.clear();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int distance = norm2(points[i], points[j]);
                    map.put(distance, map.getOrDefault(distance, 0) + 1);
                }
            }
            for (int n : map.values()) {
                // 顺序可变，所以是permutation
                count += n * (n - 1);
            }
        }
        return count;
    }
    private int norm2(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
