class Solution {
    public int repeatedStringMatch(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int min = lenB / lenA;
        int max = lenB / lenA + 2;
        for (int i = min; i <= max; i++) {
            String s = String.join("", Collections.nCopies(i, A));
            if (s.indexOf(B) != -1) {
                return i;
            }
        }
        return -1;
    }
}
