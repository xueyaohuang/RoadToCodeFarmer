class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null) {
            return false;
        }
        if (coordinates.length <= 2) {
            return true;
        }
        int x1 = coordinates[0][0];
        int y1 = coordinates[0][1];
        int x2 = coordinates[1][0];
        int y2 = coordinates[1][1];
        // 不计算斜率，把除法变乘法，避免divide by zero
        for (int i = 2; i < coordinates.length; i++) {
            int x = coordinates[i][0];
            int y = coordinates[i][1];
            if ((y - y1) * (x - x2) != (y - y2) * (x - x1)) {
                return false;
            }
        }
        return true;
    }
}

class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null) {
            return false;
        }
        if (coordinates.length <= 2) {
            return true;
        }
        int[] pointA = coordinates[0];
        int[] pointB = coordinates[1];
        if (pointA[0] == pointB[0]) {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][0] != pointA[0]) {
                    return false;
                }
            }
        } else {
            double k = (pointA[1] - pointB[1]) / (pointA[0] - pointB[0]);
            double b = pointA[1] - k * pointA[0];
            for (int i = 2; i < coordinates.length; i++) {
                if (!onLine(k, b, coordinates[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean onLine(double k, double b, int[] point) {
        return Math.abs(point[1] - k * point[0] - b) <= 0.00001;
    }
}
