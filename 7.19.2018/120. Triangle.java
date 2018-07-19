class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        list.add(triangle.get(0).get(0));
        for (int i  = 1; i < triangle.size(); i++) {
            list.add(0, list.get(0) + triangle.get(i).get(0));
            for (int j = 1; j < i; j++) {
                list.set(j, triangle.get(i).get(j) + Math.min(list.get(j), list.get(j + 1)));
            }
            list.set(i, list.get(i) + triangle.get(i).get(i));
        }
        int res = Integer.MAX_VALUE;
        for (int i : list) {
            res = Math.min(res, i);
        }
        return res;
    }
}
