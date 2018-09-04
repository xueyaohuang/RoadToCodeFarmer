class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int len = bits.length;
        int i = 0;
        while (i < len - 1) {
            if (bits[i] == 1) { // 遇到1，只能是2位，前进2
                i += 2;
            } else { // 遇到0，一定是1位，前进1
                i += 1;
            }
        }
        return i != len;
    }
}
