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
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) {
            return new ArrayList<>();
        }
        List<Interval> res = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();
        List<Interval> ans = new ArrayList<>();
        
        for (List<Interval> itvs : schedule) {
            intervals.addAll(itvs);
        }
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        res.add(intervals.get(0));
        int len = intervals.size();
        for (int i = 1; i < len; i++) {
            Interval itv = intervals.get(i);
            if (itv.start > res.get(res.size() - 1).end) {
                res.add(itv);
            } else {
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, itv.end);
            }
        }
        
        int size = res.size();
        for (int i = 0; i < size - 1; i++) {
            ans.add(new Interval(res.get(i).end, res.get(i + 1).start));
        }
        return ans;
    }
}
