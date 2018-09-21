// 记录每一层缝隙的位置，找到缝隙最多的地方，然后用高度减去缝隙的个数。
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall) {
            int size = list.size();
            int sum = 0;
            for (int i = 0; i < size - 1; i++) {
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int height = wall.size();
        int minCut = height;
        for (int end : map.keySet()) {
            minCut = Math.min(minCut, height - map.get(end));
        }
        return minCut;
    }
}
