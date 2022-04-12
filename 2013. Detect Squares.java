class DetectSquares {
    
    int[][] plane;
    List<int[]> points;

    public DetectSquares() {
        plane = new int[1001][1001];
        points = new ArrayList<>();
    }
    
    public void add(int[] point) {
        plane[point[0]][point[1]]++;
        points.add(point);
    }
    
    public int count(int[] point) {
        int x1 = point[0], y1 = point[1];
        int res = 0;
        for (int[] p : points) {
            int x3 = p[0], y3 = p[1];
            if (x1 == x3 || y1 == y3 || Math.abs(x1 - x3) != Math.abs(y1 - y3)) {
                continue;
            }
            res += plane[x1][y3] * plane[x3][y1];
        }
        return res;
    }
}
