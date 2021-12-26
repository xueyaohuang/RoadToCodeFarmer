/*
I've come up with a simple proof of this algo.

    init with all 1

    we only give one more when we see adjacent increase (both from left to right)

    I want to use some case to illustrate this ((notices we don't care the exact value of ratings, only their relation)

the rating is similar to 'wave', increase of decrease

let's look at this case:
ratings: [1,3,6,9,4,2]
candies: [1,2,3,4,2,1]

the highest rating is 9, when scan from left to right, num of candies should be 4, scan right to left, it should be 3, then we choose the larger value.

and let's look at some case containing adjacent-equal

when there are adjacent-equal, lets look at some cases:

ratings: [1,2,3,3,3,3,4,5]
candies: [1,2,3,1,1,1,2,3]

ratings: [1,2,8,8,8,8,8,2]
candies: [1,2,3,1,1,1,2,1]

with these cases it should be easier to understand.
*/

class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int len = ratings.length;
        int[] candies = new int[len];
        Arrays.fill(candies, 1);
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        // 把第二个for loop和第三个for loop结合起来
        int sum = candies[len - 1];
        for (int i = len - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                candies[i - 1] = Math.max(candies[i] + 1, candies[i - 1]);
            }
            sum += candies[i - 1];
        }
        // int sum = 0;
        // for (int num : candies) {
        //     sum += num;
        // }
        return sum;
    }
}
