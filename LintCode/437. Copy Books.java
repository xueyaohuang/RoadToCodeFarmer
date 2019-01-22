public class Solution {
    /**
     * @param pages: an array of integers
     * @param k: An integer
     * @return: an integer
     */
    public int copyBooks(int[] pages, int k) {
        // write your code here
        if (pages == null || pages.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int page : pages) {
            sum += page;
        }
        int start = sum / pages.length;
        int end = sum;
        while (start < end) {
            int mid = (start + end) / 2;
            if (canFinish(pages, mid, k)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
    
    private boolean canFinish(int[] pages, int maxPage, int k) {
        int num = 0;
        int left = 0;
        for (int page : pages) {
            if (page > maxPage) {
                return false;
            }
            if (page > left) {
                num++;
                left = maxPage;
            }
            left -= page;
        }
        return num <= k;
    }
}
