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
        int start, end, i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) res.add(intervals.get(i++));
        if (i == intervals.size()) res.add(newInterval);
        else {
            start = Math.min(newInterval.start, intervals.get(i).start);
            while (i < intervals.size() && intervals.get(i).start <= newInterval.end) i++;
            end = i == 0 ? newInterval.end : Math.max(newInterval.end, intervals.get(i - 1).end);
            res.add(new Interval(start, end));
            while (i < intervals.size()) res.add(intervals.get(i++));
        }
        return res;
    }
}
