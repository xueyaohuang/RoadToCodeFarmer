class Solution {
    public String[] expand(String s) {
        List<List<String>> letters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') {
                List<String> temp = new ArrayList<>();
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        temp.add(String.valueOf(s.charAt(i)));
                    }
                    i++;
                }
                letters.add(temp);
            } else {
                List<String> temp = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                while (i < s.length() && Character.isLetter(s.charAt(i))) {
                    sb.append(s.charAt(i));
                    i++;
                }
                i--;
                temp.add(sb.toString());
                letters.add(temp);
            }
        }
        List<String> res = new ArrayList<>();
        backtracking(letters, 0, res, new StringBuilder());
        Collections.sort(res);
        return res.toArray(new String[0]);
    }
    
    private void backtracking(List<List<String>> letters, int idx, List<String> res, StringBuilder sb) {
        if (idx == letters.size()) {
            res.add(sb.toString());
            return;
        }
        List<String> cur = letters.get(idx);
        for (int i = 0; i < cur.size(); i++) {
            sb.append(cur.get(i));
            backtracking(letters, idx + 1, res, sb);
            sb.setLength(sb.length() - 1);
        }
    }
}
