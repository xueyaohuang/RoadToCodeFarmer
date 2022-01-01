// sweep line
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] seats = new int[n + 1];
        for (int[] booking : bookings) {
            seats[booking[0] - 1] += booking[2];
            seats[booking[1]] -= booking[2];
        }
        int acc = 0;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            acc += seats[i];
            res[i] = acc;
        }
        return res;
    }
}
