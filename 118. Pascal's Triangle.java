class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (numRows <= 0) {
            return list;
        }
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                }
                else {
                    temp.add(list.get(i - 1).get(j - 1) + list.get(i - 1).get(j));
                }        
            }
            list.add(temp);
        }
        return list;
    }
}
