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
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        int lenA = A.length;
        int lenB = B.length;
        int i = 0, j = 0;
        List<Interval> res = new ArrayList<>();
        while (i < lenA && j < lenB) {
            if (hasIntersection(A[i], B[j])) {
                res.add(getIntersection(A[i], B[j]));
            }
            if (A[i].end < B[j].end) {
                i++;
            } else if (A[i].end > B[j].end) {
                j++;
            } else {
                i++;
                j++;
            }
        }
        return res.toArray(new Interval[res.size()]);
    }
    
    private boolean hasIntersection(Interval a, Interval b) {
        return Math.max(a.start, b.start) <= Math.min(a.end, b.end);
    }
    
    private Interval getIntersection(Interval a, Interval b) {
        return new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end));
    }
}
