/*
1. Sort both slots1 and slots2 by the start time.
2. Initialize two pointers, i and j, pointing to the beginning of slots1 and the beginning of slots2 respectively.
3. Iterate until i reaches the end of slots1 or j reaches the end of slots2:
  Find the intersect slot between slots1[i] and slots2[j]
  If the intersect slot >= duration, return the result.
  Else, find the slot that ends earlier and move the pointer.
4.If no result is found, return an empty array.
*/
class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int i = 0, j = 0;
        while (i < slots1.length && j < slots2.length) {
            if (getCommonTime(slots1[i], slots2[j]) >= duration) {
                int start = Math.max(slots1[i][0], slots2[j][0]);
                return Arrays.asList(start, start + duration);
            } else if (slots1[i][1] <= slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return new ArrayList<>();
    }
    
    private int getCommonTime(int[] slot1, int[] slot2) {
        int commonTime = Math.min(slot1[1], slot2[1]) - Math.max(slot1[0], slot2[0]);
        return commonTime > 0 ? commonTime : 0;
    }
}
