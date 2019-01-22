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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        for (Interval itv : intervals) {
            if (newInterval == null || itv.end < newInterval.start) {
                res.add(itv);
            } else if (itv.start > newInterval.end) {
                res.add(newInterval);
                res.add(itv);
                newInterval = null;
            } else {
                newInterval.start = Math.min(itv.start, newInterval.start);
                newInterval.end = Math.max(itv.end, newInterval.end);
            }
        }
        if (newInterval != null) {
            res.add(newInterval);
        }
        return res;
    }
}
