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
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
//         Collections.sort(intervals, (a, b) -> a.start - b.start);
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

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] itv = stack.peek();
            if (itv[1] < intervals[i][0]) {
                stack.push(intervals[i]);
            } else {
                stack.pop();
                stack.push(new int[]{itv[0], Math.max(itv[1], intervals[i][1])});
            }
        }
        int size = stack.size();
        int[][] res = new int[size][2];
        for (int i = size - 1; i >= 0; i--) {
            res[i] = stack.pop();
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
