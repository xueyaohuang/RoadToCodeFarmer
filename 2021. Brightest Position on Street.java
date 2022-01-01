// sweep line
// 只需要考虑端点
class Solution {
    public int brightestPosition(int[][] lights) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] light : lights) {
            int start = light[0] - light[1];
            int end = light[0] + light[1] + 1; // end 这里要加1，因为end时机上是能被照亮的，要在end后面一个idx才减一
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }
        int acc = 0;
        int max = 0;
        int res = 0;
        for (int i : map.keySet()) {
            acc += map.get(i);
            if (acc > max) {
                res = i;
                max = acc;
            }
        }
        return res;
    }
}
