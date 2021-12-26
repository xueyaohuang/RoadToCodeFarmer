/*
Similar Problems

    https://leetcode.com/problems/divide-chocolate/
    https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
    https://leetcode.com/problems/split-array-largest-sum/
    https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    https://leetcode.com/problems/minimize-max-distance-to-gas-station/
    https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/
*/
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int start = 1;
        int end = (position[n - 1] - position[0]) / (m - 1);
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (canPlace(position, m, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    private boolean canPlace(int[] position, int m, int distance) {
        int n = position.length;
        int prev = position[0];
        int count = 1;
        for (int i = 1; i < n; i++) {
            if (position[i] - prev >= distance) {
                count++;
                prev = position[i];
            }
            if (count == m) {
                return true;
            }
        }
        return count >= m;
    }
}
