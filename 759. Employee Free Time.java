/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// 类似于lc23. Merge k Sorted Lists
// O(nlgk). n is the number of total intervals, k is the number of employees
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> schedule.get(a[0]).get(a[1]).start - schedule.get(b[0]).get(b[1]).start);
        // Populate the priority queue with the first interval from each list
        for (int i = 0; i < schedule.size(); i++) {
            pq.offer(new int[]{i, 0});
        }
        int prev = schedule.get(pq.peek()[0]).get(pq.peek()[1]).end;
        List<Interval> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] idx = pq.poll();
            // No overlap, so add the gap in intervals
            if (schedule.get(idx[0]).get(idx[1]).start > prev) {
                res.add(new Interval(prev, schedule.get(idx[0]).get(idx[1]).start));
            }
            prev = Math.max(prev, schedule.get(idx[0]).get(idx[1]).end);
            if (schedule.get(idx[0]).size() > idx[1] + 1) {
                pq.offer(new int[]{idx[0], idx[1] + 1});
            }
        }
        return res;
    }
}

// O(nlgk), merge sort
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int n=schedule.size();
        List<Interval> time=mergeSort(schedule, 0, n-1);
        List<Interval> free=new ArrayList<>();
        int end=time.get(0).end;
        for(int i=1;i<time.size();i++) {
            if(time.get(i).start>end) {
                free.add(new Interval(end, time.get(i).start));
            }
            end=Math.max(end, time.get(i).end);
        }
        return free;
    }
    
    private List<Interval> mergeSort(List<List<Interval>> schedule, int l, int r) {
        if(l==r) return schedule.get(l);
        int mid=(l+r)/2;
        List<Interval> left=mergeSort(schedule, l, mid);
        List<Interval> right=mergeSort(schedule, mid+1, r);
        return merge(left, right);
    }
    
    private List<Interval> merge (List<Interval> A, List<Interval> B) {
        List<Interval> res=new ArrayList<>();
        int m=A.size(), n=B.size();
        int i=0, j=0;
        while(i<m||j<n) {
            if(i==m) {
                res.add(B.get(j++));
            }
            else if(j==n) {
                res.add(A.get(i++));
            }
            else if(A.get(i).start<B.get(j).start) {
                res.add(A.get(i++));
            }
            else res.add(B.get(j++));
        }
        return res;
    }
}

// O(nlgn),这种方法题目的一条信息没有用上“these intervals are in sorted order.”
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
