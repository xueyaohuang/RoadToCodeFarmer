class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i + 1 < s.length(); i++) {
            if (s.substring(i, i + 2).equals("++")) {
                StringBuilder sb = new StringBuilder(s);
                sb.replace(i, i + 2, "--");
                list.add(sb.toString());
            }
        }
        return list;
    }
}
