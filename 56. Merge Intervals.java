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

// https://leetcode.com/problems/merge-intervals/discuss/21223/Beat-98-Java.-Sort-start-and-end-respectively.
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return list;
        }
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for(int i = 0, j = 0; i < intervals.size(); i++) {
            if (i == intervals.size() - 1 || start[i + 1] > end[i]) {
                list.add(new Interval(start[j], end[i]));
                j = i + 1;
            }
        }
        return list;
    }
}
