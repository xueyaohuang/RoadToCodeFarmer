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
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int n = intervals.length;
        int[] startTime = new int[n];
        int[] endTime = new int[n];
        for (int i = 0; i < n; i++) {
            startTime[i] = intervals[i].start;
            endTime[i] = intervals[i].end;
        }
        Arrays.sort(startTime);
        Arrays.sort(endTime);
        int room = 1;
        int endRoom = 0;
        for (int i = 1; i < n; i++) {
            if (startTime[i] < endTime[endRoom]) {
                room++;
            }
            else {
                endRoom++;
            }
        }
        return room;
    }
}

public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>(); // Sort Key based on nature order
        for (Interval i : intervals) {
            if (map.containsKey(i.start)) {
                map.put(i.start, map.get(i.start)+1);
            } else {
                map.put(i.start, 1);
            }
            if (map.containsKey(i.end)) {
                map.put(i.end, map.get(i.end)-1);
            } else {
                map.put(i.end, -1);
            }
        }
        int maxRoom = 0; int curRoom = 0;
        for (int i : map.keySet()) {
            maxRoom = Math.max(maxRoom, curRoom += map.get(i));
        }
        return maxRoom;
    }
}
