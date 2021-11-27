/*
Key Idea
In a 2D matrix, elements in the same diagonal have same sum of their indices.
So if we have all elements with same sum of their indices together, then it’s just a matter of printing those elements in order.

Algorithm
1. Insert all elements into an appropriate bucket i.e. nums[i][j] in (i+j)th bucket.
2. For each bucket starting from 0 to max, print all elements in the bucket.
Note: Here, diagonals are from bottom to top, we traverse all lists in reverse order.

*/

class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int count = 0;
        int maxIdx = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = nums.size() - 1; i >= 0; i--) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int idx = i + j;
                maxIdx = Math.max(maxIdx, idx);
                map.putIfAbsent(idx, new ArrayList<Integer>());
                map.get(idx).add(nums.get(i).get(j));
                count++;
            }
        }
        int[] res = new int[count];
        count = 0;
        for (int i = 0; i <= maxIdx; i++) {
            for (int j : map.get(i)) {
                res[count++] = j;
            }
        }
        return res;
    }
}
