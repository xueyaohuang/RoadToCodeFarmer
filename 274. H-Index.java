// O(n), bucket sort
class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int c : citations) {
            if (c <= n) {
                bucket[c]++;
            } else {
                bucket[n]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[i];
            if (count >= i) {
                return i;
            }
        }
        return 0;
    }
}

// O(nlgn)
class Solution {
    public int hIndex(int[] citations) {
        int start = 0, end = citations.length;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (canHaveIndex(citations, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    
    private boolean canHaveIndex(int[] citations, int index) {
        int count = 0;
        for (int c : citations) {
            if (c >= index) {
                count++;
            }
        }
        return count >= index;
    }
}
