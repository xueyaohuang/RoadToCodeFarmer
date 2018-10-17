
// average: 有k种y，每种n/k个点, k*(n/k)lg(n/k)=nlg(n/k)
// worst case: 一种k，n个点，sort需要很长时间，nlgn
class Solution {
    public boolean isReflected(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int mid = 0;
        int count = 0;
        for (int[] point : points) {
            if (!map.containsKey(point[1])) {
                map.put(point[1], new HashSet<>());
            }
            map.get(point[1]).add(point[0]);
        }
        
        for (Set<Integer> set : map.values()) {
            List<Integer> xs = new ArrayList<>(set);
            Collections.sort(xs);
            int size = xs.size();
            if (count == 0) {
                mid = xs.get(0) + xs.get(size - 1);
                count++;
            }
            for (int i = 0; i <= size / 2; i++) {
                if (xs.get(i) + xs.get(size - 1 - i) != mid) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean isReflected(int[][] points) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        Set<String> set = new HashSet<>();
        
        for (int[] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            set.add(p[0] + "," + p[1]);
        }
        
        int sum = min + max;
        
        for (String s : set) {
            String[] strs = s.split(",");
            int x = sum - Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);
            String oppo = x + "," + y;
            if (!set.contains(oppo)) {
                return false;
            }
        }
        return true;
    }
}
