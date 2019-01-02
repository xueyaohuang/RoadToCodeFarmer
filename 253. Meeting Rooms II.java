/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// best
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        // Arrays.sort(intervals, (a, b) -> a.start - b.start);
        int count = 0;
        // pq里放的是还没开完的会议的结束时间，结果是pq的size的最大值
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            //some meeting has already ended before the start of the next meeting, poll those meetings out
            while (!pq.isEmpty() && intervals[i].start >= pq.peek()) {
                pq.poll();
            }
            pq.offer(intervals[i].end);
            count = Math.max(count, pq.size());
        }
        return count;
    }
}

// 最差O(n^2)
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        // list的size代表需要room个数，list的元素是这个room的结束时间。
        List<Integer> list = new ArrayList<>();
        int rooms = 1;
        list.add(intervals[0].end);
        for (int i = 1; i < intervals.length; i++) {
            int j = 0;
            for (; j < list.size(); j++) {
                // 找到一个可以继续开会的房间就break，或者遍历完list都没找到也break
                if (list.get(j) <= intervals[i].start) {
                    break;
                }
            }
            if (j < list.size()) {  // go to jth meeting room
                list.set(j, intervals[i].end);
            } else {
                list.add(intervals[i].end);
                rooms++;
            }  
        }
        return list.size(); // return room;
    }
}

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
