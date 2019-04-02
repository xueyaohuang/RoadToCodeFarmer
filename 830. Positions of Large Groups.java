class Solution {
    public static List<List<Integer>> largeGroupPositions(String S) {
        if (S == null || S.length() < 3) {
            return new ArrayList<>();
        }
        int len = S.length();
        char[] chs = S.toCharArray();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int end = i;
            while (end < len - 1 && chs[end] == chs[end + 1]) {
                end++;
            }
            if (end - i + 1 >= 3) {
                res.add(Arrays.asList(i, end));
            }
            i = end;
        }
        return res;
    }
}
