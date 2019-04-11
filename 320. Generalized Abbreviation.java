/*
The idea is: for every character, we can keep it or abbreviate it. To keep it, we add it to the current solution
and carry on backtracking. To abbreviate it, we omit it in the current solution, but increment the count, which
indicates how many characters have we abbreviated. When we reach the end or need to put a character in the current
solution, and count is bigger than zero, we add the number into the solution.
*/
class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        backtracking(word, res, 0, new StringBuilder(), 0);
        return res;
    }
    
    private void backtracking(String word, List<String> res, int start, StringBuilder sb, int count) {
        if (start == word.length()) {
            if (count > 0) {
                sb.append(count);
            }
            res.add(sb.toString());
            return;
        }
        int len = sb.length();
        backtracking(word, res, start + 1, sb, count + 1);
        sb.setLength(len);
        backtracking(word, res, start + 1, sb.append(count > 0 ? count : "").append(word.charAt(start)), 0);
        sb.setLength(len);
    }
}
