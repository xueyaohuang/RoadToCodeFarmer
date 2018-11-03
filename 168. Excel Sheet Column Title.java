class Solution {
    public String convertToTitle(int n) {
        if (n <= 0) {
            return "";
        }
        int x = n;
        StringBuilder sb = new StringBuilder();
        while (x > 0) {
            sb.append((char)((x - 1) % 26 + 'A'));
            x = (x - 1) / 26;
        }
        return sb.reverse().toString();
    }
}
