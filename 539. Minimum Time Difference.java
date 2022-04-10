// There is only 24 * 60 = 1440 possible time points. Just create a boolean array, each element stands for if we see that time point or not.
// the problem is another version of finding the shortest distances between two elements in a circular array.
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        // 1440 = 60 * 24
        boolean[] times = new boolean[1440];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int t = getMinutes(timePoints.get(i));
            if (times[t]) {
                return 0;
            }
            times[t] = true;
            min = Math.min(min, t);
            max = Math.max(max, t);
        }
        // 720 = 60 * 12
        int res = 720;
        int prev = min;
        for (int i = min + 1; i <= max; i++) {
            if (times[i]) {
                res = Math.min(res, i - prev);
                prev = i;
            }
        }
        res = Math.min(res, Math.min(max - min, 1440 - (max - min)));
        return res;
    }
    
    private int getMinutes(String s) {
        String[] parts = s.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}

// sort
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = getMinutes(timePoints.get(i));
        }
        Arrays.sort(times);
        int res = 720;
        for (int i = 0; i < n - 1; i++) {
            int diff = times[i + 1] - times[i];
            res = Math.min(res, Math.min(diff, 1440 - diff));
        }
        int last = times[n - 1] - times[0];
        res = Math.min(res, Math.min(last, 1440 - last));
        return res;
    }
    
    private int getMinutes(String s) {
        String[] parts = s.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
