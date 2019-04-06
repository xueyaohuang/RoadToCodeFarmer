class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0;
        for (int w : weights) {
            sum += w;
        }
        int start = sum / D;
        int end = sum;
        while (start < end) {
            int mid = (start + end) / 2;
            if (canFinish(weights, D, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private boolean canFinish(int[] weights, int D, int cap) {
        int count = 0;
        int left = 0;
        for (int w : weights) {
            if (w > cap) {
                return false;
            }
            if (w > left) {
                left = cap;
                count++;
            }
            left -= w;
        }
        return count <= D;
    }
}
