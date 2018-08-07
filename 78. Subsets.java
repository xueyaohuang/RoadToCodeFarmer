class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<Integer>());
        if (nums == null || nums.length == 0) {
            return list;
        }
        for (int num : nums) {
            int tmpsize = list.size();
            for (int i = 0; i < tmpsize; i++) {
                List<Integer> temp = new ArrayList<>(list.get(i));
                temp.add(num);
                list.add(temp);
            }
        }
        return list;
    }
}
