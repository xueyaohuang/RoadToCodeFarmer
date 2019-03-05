// O(n2)
class Solution {
    public int minAreaRect(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int minArea = Integer.MAX_VALUE;
        for (int[] point : points) {
            set.add(point[0] + "," + point[1]);
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) {
                    continue;
                }
                if (set.contains(points[i][0] + "," + points[j][1]) && set.contains(points[j][0] + "," + points[i][1])) {
                    minArea = Math.min(minArea, Math.abs(points[j][0] - points[i][0]) * Math.abs(points[j][1] - points[i][1]));
                } 
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
}

// Hashmap version
class Solution {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : points) {
            if (!map.containsKey(p[0])) {
                map.put(p[0], new HashSet<>());
            }
            map.get(p[0]).add(p[1]);
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                //if have the same x or y
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) {
                    continue;
                }
                //find 2 points
                //p1,p2 are used to find diagonal for the rect. need 4 point to check a rect.
                if (map.get(points[i][0]).contains(points[j][1]) && map.get(points[j][0]).contains(points[i][1])) {
                    min = Math.min(min, Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]));
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
