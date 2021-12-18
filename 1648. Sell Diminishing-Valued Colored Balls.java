// O(nlgn) inventory.length
/*
1  3  5  5  9  9  9    original sorted inventory
1  3  5  5  5  5  5    profit gain after selling all 9-value balls: (9 + 8 + 7 + 6) * 3 -> (9 + 6) * (9 - 6 + 1) / 2 * 3
1  3  3  3  3  3  3    (5 + 4) * (5 - 4 + 1) / 2 * 5  -> (curValue + nextValue + 1) * (curValue - nextValue) / 2 * numSameColor
1  1  1  1  1  1  1
0  0  0  0  0  0  0

need to handle the edge case that orders left is less than the number of items to buy
*/
class Solution {
    public int maxProfit(int[] inventory, int orders) {
        int mod = (int)Math.pow(10, 9) + 7;
        long res = 0;
        Arrays.sort(inventory);
        int curIndex = inventory.length - 1;
        int curValue = inventory[curIndex];
        while (orders > 0) {
            while (curIndex >= 0 && inventory[curIndex] == curValue) {
                curIndex--;
            }
            // if all colors of balls are the same value, nextValue is 0
            int nextValue = curIndex < 0 ? 0 : inventory[curIndex];
            // number of colors of balls with same value 
            int numOfSameValue = inventory.length - 1 - curIndex;
            int addTimes = curValue - nextValue;
            // number of balls to buy
            int addBalls = numOfSameValue * addTimes;
            if (orders >= addBalls) {
                // buy all items
                res += (long)(curValue + nextValue + 1) * addTimes / 2 * numOfSameValue;
                orders -= addBalls;
            } else {
                // orders left is less than the number of items to buy
                int numOfSameValueRow = orders / numOfSameValue;
                int reminder = orders % numOfSameValue;
                res += (long)(curValue + curValue - numOfSameValueRow + 1) * numOfSameValueRow / 2 * numOfSameValue;
                res += (long)(curValue - numOfSameValueRow) * reminder;
                orders = 0;
            }
            res %= mod;
            curValue = nextValue;
        }
        return (int)res;
    }
}


// O(mlgn), m = orders, n = inventory.length
// this is good if orders is small
class Solution {
    public int maxProfit(int[] inventory, int orders) {
        int mod = (int)Math.pow(10, 9) + 7;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : inventory) {
            pq.offer(i);
        }
        int res = 0;
        for (int i = 0; i < orders; i++) {
            int curMax = pq.poll();
            res += curMax;
            res %= mod;
            pq.offer(curMax - 1);
        }
        return res;
    }
}
