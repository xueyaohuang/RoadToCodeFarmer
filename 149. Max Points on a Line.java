/*
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

/*
核心思想：多个点共线，那么他们之间任意两点组成直线的斜率一样，或者两个点重合。
*/

class Solution {
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        // key：两个点横坐标之差除以gcd. i.e. gcd = gcd(x1-x0, y1-y0). key=(x1-x0)/gcd
        // value：是一个map。key：两个点纵坐标之差除以gcd，value：共线的点的个数（不包括重合的点）。
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear(); // result对每个点都会给更新一次，所以每次计算新的点都要clear map
            int overlap = 0; // 两个点重合
            // 对每个点，都可能有很多种不同斜率的共线，max就是对某一个点，不同斜率共线的最大个数。
            int max = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    overlap++;
                    continue;
                }
                // gcd肯定不是0
                int gcd = generateGCD(x, y);
                // 一对(x,y)代表了一种斜率，直接用小数表示斜率当map的key会出现相差极小分不清楚的情况，比如1和1.000000000000001
                x /= gcd;
                y /= gcd;
                if (map.containsKey(x)) {
                    if (map.get(x).containsKey(y)) {
                        map.get(x).put(y, map.get(x).get(y) + 1);
                    } else {
                        map.get(x).put(y, 1); 
                    }
                } else {
                    Map<Integer, Integer> temp = new HashMap<>();
                    temp.put(y, 1);
                    map.put(x, temp);
                }
                max = Math.max(max, map.get(x).get(y));
            }
            result = Math.max(result, max + overlap + 1);
        }
        return result;
    }
    private int generateGCD(int a, int b) {
        return b == 0 ? a : generateGCD(b, a % b);
    }
}
