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
        // 把第二个for loop个第三个for loop结合起来
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
