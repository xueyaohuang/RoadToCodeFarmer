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
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<>();
        }
        Collections.sort(intervals, (a, b) -> a.start - b.start);
        int len = intervals.size();
        List<Interval> res = new ArrayList<>();
        res.add(intervals.get(0));
        
        for (int i = 1; i < len; i++) {
            Interval last = res.get(res.size() - 1);
            if (last.end >= intervals.get(i).start) {
                last.end = Math.max(last.end, intervals.get(i).end);
            } else {
                res.add(intervals.get(i));
            }
        }
        return res;
    }
}
