// 1. 先选出最大值max1，然后从剩下list中选出最小值min1，得到结果res1
// 2. 先选出最小值min2，然后从剩下list中选出最大值max2，得到结果res2
// 最终结果是res1和res2中较大的那个
class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int size = arrays.size();
        int max1 = Integer.MIN_VALUE;
        int maxIdx1 = 0;
        for (int i = 0; i < size; i++) {
            List<Integer> cur = arrays.get(i);
            int len = cur.size();
            if (cur.get(len - 1) > max1) {
                max1 = cur.get(len - 1);
                maxIdx1 = i;
            }
        }
        int min1 = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (i != maxIdx1) {
                List<Integer> cur = arrays.get(i);
                if (cur.get(0) < min1) {
                    min1 = cur.get(0);
                }
            }
        }
        
        int min2 = Integer.MAX_VALUE;
        int minIdx2 = 0;
        for (int i = 0; i < size; i++) {
            List<Integer> cur = arrays.get(i);
            if (cur.get(0) < min2) {
                min2 = cur.get(0);
                minIdx2 = i;
            }
        }
        
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (i != minIdx2) {
                List<Integer> cur = arrays.get(i);
                int len = cur.size();
                if (cur.get(len - 1) > max2) {
                    max2 = cur.get(len - 1);
                }
            }
        }
        
        return Math.max(max1 - min1, max2 - min2);
    }
}

class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
        int result = Integer.MIN_VALUE;
        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int min = arrays.get(0).get(0);
        
        for (int i = 1; i < arrays.size(); i++) {
            int size=arrays.get(i).size();
            
            int currMin=arrays.get(i).get(0);
            int currMax=arrays.get(i).get(size - 1);
            
            result = Math.max(result, Math.abs(currMin - max));
            result = Math.max(result, Math.abs(currMax - min));
            
            max = Math.max(max, currMax);
            min = Math.min(min, currMin);
        }
        return result;
    }
}
