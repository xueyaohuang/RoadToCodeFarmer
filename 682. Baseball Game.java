class Solution {
    public int calPoints(String[] ops) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                int last1 = list.get(list.size() - 1);
                int last2 = list.get(list.size() - 2);
                int cur = last1 + last2;
                sum += cur;
                list.add(cur);
            }
            else if (ops[i].equals("D")) {
                int last = list.get(list.size() - 1);
                int cur = last * 2;
                sum += cur;
                list.add(cur);
            }
            else if (ops[i].equals("C")) {
                int last = list.get(list.size() - 1);
                sum -= last;
                list.remove(list.size() - 1);
            }
            else {
                int cur = Integer.valueOf(ops[i]);
                list.add(cur);
                sum += cur;                
            }
        }
        return sum;
    }
}
