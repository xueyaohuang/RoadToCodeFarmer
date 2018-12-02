class Solution {
    public int maxDistToClosest(int[] seats) {
        int i = 0, j = 0;
        int res = Integer.MIN_VALUE;
        int len = seats.length;
        for (; j < len; j++) {
            if (seats[j] == 1) {
                if (i == 0 && seats[i] == 0) {
                    res = Math.max(res, j - i);
                } else {
                    res = Math.max(res, (j - i) / 2);
                }
                i = j;
            }
        }
        return Math.max(res, len - 1 - i);
    }
}

class Solution {
    public int maxDistToClosest(int[] seats) {
        int len = seats.length;
        int firstOne = 0;
        int lastOne = len - 1;
        
        while (firstOne < len && seats[firstOne] == 0) {
            firstOne++;
        }
        while (lastOne >= 0 && seats[lastOne] == 0) {
            lastOne--;
        }
        
        if (firstOne == lastOne) {
            return Math.max(firstOne, len - 1 - lastOne);
        }
        
        int prev = firstOne;
        int res = Integer.MIN_VALUE;
        for (int i = firstOne + 1; i <= lastOne; i++) {
            if (seats[i] == 1) {
                res = Math.max(res, (i - prev) / 2);
                prev = i;
            }
        }
        return Math.max(Math.max(firstOne, res), len - 1 - lastOne);
    }
}
