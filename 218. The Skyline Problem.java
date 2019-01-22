class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] building : buildings) {
            // start point has negative height value
            height.add(new int[]{building[0], -building[2]});
            // end point has normal height value
            height.add(new int[]{building[1], building[2]});
        }
        Collections.sort(height, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                } else {
                    return a[0] - b[0];
                }
            }
        });
        // Use a maxHeap to store possible heights
        // But priority queue does not support remove in lgn time
        // treemap support add, remove, get max in lgn time, so use treemap here
        // key: height, value: number of this height
        TreeMap<Integer, Integer> pq = new TreeMap<>();
        pq.put(0, 1);
        // Before starting, the previous max height is 0;
        int prev = 0;
        // visit all points in order
        for (int[] h : height) {
            // a start point, add height
            if (h[1] < 0) {
                pq.put(-h[1], pq.getOrDefault(-h[1], 0) + 1);
            } else {  // a end point, remove height
                if (pq.get(h[1]) > 1) {
                    pq.put(h[1], pq.get(h[1]) - 1);
                } else {
                    pq.remove(h[1]);
                }
            }
            int cur = pq.lastKey();
            // compare current max height with previous max height, update result and 
            // previous max height if necessary
            if (cur != prev) {
                res.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return res;
    }
}
