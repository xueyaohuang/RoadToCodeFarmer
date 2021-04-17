/*
核心思想：多个点共线，那么他们之间任意两点组成直线的斜率一样，或者两个点重合。
*/

class Solution {
    public int maxPoints(int[][] points) {
        Map<String, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear(); // result对每个点都会给更新一次，所以每次计算新的点都要clear map
            int overlap = 0; // 两个点重合
            // 对每个点，都可能有很多种不同斜率的共线，max就是对某一个点，不同斜率共线的最大个数。
            int max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                // gcd肯定不是0
                int gcd = generateGCD(x, y);
                // 一对(x,y)代表了一种斜率，直接用小数表示斜率当map的key会出现相差极小分不清楚的情况，比如1和1.000000000000001
                x /= gcd;
                y /= gcd;
                String  slope = x + " " + y;
                map.put(slope, map.getOrDefault(slope, 0) + 1);
                max = Math.max(max, map.get(slope));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }
    private int generateGCD(int a, int b) {
        return b == 0 ? a : generateGCD(b, a % b);
    }
}
