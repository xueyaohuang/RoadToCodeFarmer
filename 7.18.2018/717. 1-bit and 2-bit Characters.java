class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        int i = 0;
        while (i + 1 < len) {
            if (bits[i] == 1) {
                i += 2;
            }
            else {
                i++;
            }
        }
        return i != len;
    }
}
