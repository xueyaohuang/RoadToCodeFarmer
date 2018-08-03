class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return new ArrayList<Interval>();
        }
        List<Interval> res = new ArrayList<Interval>();
        Collections.sort(intervals, (a, b) -> a.start - b.start); // 注意是Collections.sort，不是Arrays.sort.
        for (Interval itv : intervals) {
            if (res.size() == 0 || itv.start > res.get(res.size() - 1).end) {
                res.add(itv);
            } else {
                res.get(res.size() - 1).end = Math.max(res.get(res.size() - 1).end, itv.end);
            }
        }
        return res;
    }
}
