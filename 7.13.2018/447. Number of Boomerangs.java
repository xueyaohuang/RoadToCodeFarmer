class Solution {
    public int numberOfBoomerangs(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int distance = distanceSqare(points[i], points[j]);
                    map.put(distance, map.getOrDefault(distance, 0) + 1);
                }
            }
            for (int num : map.values()) {
                count += num * (num - 1);
            }
        }
        return count;
    }
    private int distanceSqare(int[] A, int[] B) {
        int xx = A[0] - B[0];
        int yy = A[1] - B[1];
        return xx * xx + yy * yy;
    }
}
