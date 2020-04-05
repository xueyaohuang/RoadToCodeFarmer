class Solution {
    // 10000000
    final int mask1 = 128;
    // 11000000
    final int mask2 = 192;
    // 11100000
    final int mask3 = 224;
    // 11110000
    final int mask4 = 240;
    // 11111000
    final int mask5 = 248;
    public boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length;) {
            int type = checkByte(data[i]);
            if (type == -1) {
                return false;
            }
            if (i + type > data.length) {
                return false;
            }
            if (type == 4) {
                if (!beginWithOneZero(data, i + 1, i + 3)) {
                    return false;
                }
            } else if (type == 3) {
                if (!beginWithOneZero(data, i + 1, i + 2)) {
                    return false;
                }
            } else if (type == 2) {
                if (!beginWithOneZero(data, i + 1, i + 1)) {
                    return false;
                }
            }
            i += type;
        }
        return true;
    }
    
    private int checkByte(int i) {
        if ((i & mask5) == 240) { // 11110000
            return 4;
        } else if ((i & mask4) == 224) { // 11100000
            return 3;
        } else if ((i & mask3) == 192) { // 1100000
            return 2;
        } else if ((i & mask1) == 0) {
            return 1;
        }
        return -1;
    }
    
    private boolean beginWithOneZero(int[] data, int start, int end) {
        for (int i = start; i <= end; i++) {
            if ((i & mask2) != 128) {
                return false;
            }
        }
        return true;
    }
}
