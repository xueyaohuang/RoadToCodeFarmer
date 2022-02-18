// prefix sum
public int maxScore(int[] cp, int k) {
    int n = cp.length;
    int leftsum = 0;
    for (int i = 0; i < k; i++) {
        leftsum += cp[i];
    }
    int max = leftsum;
    int rightsum = 0;

    for (int i = 0; i < k; i++) {
        leftsum  -= cp[k - 1 - i];
        rightsum += cp[n - 1 - i];
        max = Math.max(max, leftsum + rightsum);
    }

    return max;
}
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int[] forwardSum = new int[k + 1];
        int[] backwardSum = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            forwardSum[i] = forwardSum[i - 1] + cardPoints[i - 1];
        }
        for (int i = 1; i <= k; i++) {
            backwardSum[i] = backwardSum[i - 1] + cardPoints[n - i];
        }
        int res = 0;
        for (int i = 0; i <= k; i++) {
            res = Math.max(res, forwardSum[i] + backwardSum[k - i]);
        }
        return res;
    }
}
