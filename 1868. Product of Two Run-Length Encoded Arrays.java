// two pointer
class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int i = 0, j = 0;
        List<List<Integer>> res = new ArrayList<>();
        int count = 0, num = 0;
        int count1 = encoded1[0][1], count2 = encoded2[0][1];
        int num1 = encoded1[0][0], num2 = encoded2[0][0];
        while (i < encoded1.length && j < encoded2.length) {
            count = Math.min(count1, count2);
            if (num1 * num2 != num) {
                num = num1 * num2;
                res.add(Arrays.asList(num, count));
            } else {
                List<Integer> last = res.get(res.size() - 1);
                last.set(1, last.get(1) + count);
            }
           
            if (count1 < count2) {
                i++;
                if (i <= encoded1.length) {
                    count1 = encoded1[i][1];
                    num1 = encoded1[i][0];
                }
                count2 -= count;
            } else if (count1 > count2) {
                j++;
                count1 -= count;
                if (j < encoded2.length) {
                    count2 = encoded2[j][1];
                    num2 = encoded2[j][0];
                }
            } else {
                i++;
                j++;
                if (i < encoded1.length) {
                    count1 = encoded1[i][1];
                    num1 = encoded1[i][0];
                }
                if (j < encoded2.length) {
                    count2 = encoded2[j][1];
                    num2 = encoded2[j][0];
                }
            }
        }
        return res;
    }
}
