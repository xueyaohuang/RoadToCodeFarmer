/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */


class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        return true;
    }
}

// 
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        int n = intervals.length;
        int[] startTime = new int[n];
        int[] endTime = new int[n];
        int i = 0;
        for (Interval itv : intervals) {
            startTime[i] = itv.start;
            endTime[i++] = itv.end;
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        for (int j = 1; j < n; j++) {
            if (startTime[j] < endTime[j - 1]) {
                return false;
            }
        }
        return true;
    }
}
