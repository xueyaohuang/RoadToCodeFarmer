class Solution {
    public boolean queryString(String S, int N) {
        for (int i = N; i > N / 2; i--) {
            // if (S.indexOf(Integer.toBinaryString(i)) == -1) {
            //     return false;
            // }
            if (!S.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
