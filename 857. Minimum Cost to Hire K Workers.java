class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        if (quality == null || wage == null) {
            return 0.0;
        }
        
        int len = wage.length;
        double[][] ratio = new double[len][2];
        for (int i = 0; i < len; i++) {
            ratio[i] = new double[]{(double)wage[i] / quality[i], quality[i]};
        }
        Arrays.sort(ratio, (a, b) -> Double.compare(a[0], b[0]));
        
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        double res = Integer.MAX_VALUE;
        double totQ = 0;
        for (int i = 0; i < len; i++) {
            totQ += ratio[i][1];
            pq.offer(ratio[i][1]);
            if (pq.size() == K) {
                res = Math.min(res, ratio[i][0] * totQ);
                totQ -= pq.poll();
            }
        }
        return res;
    }
}
