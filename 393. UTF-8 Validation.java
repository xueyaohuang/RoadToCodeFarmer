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
                if (!beginWithOneAndZero(data[i + 1]) || !beginWithOneAndZero(data[i + 2]) || !beginWithOneAndZero(data[i + 3])) {
                    return false;
                }
            } else if (type == 3) {
                if (!beginWithOneAndZero(data[i + 1]) || !beginWithOneAndZero(data[i + 2])) {
                    return false;
                }
            } else if (type == 2) {
                if (!beginWithOneAndZero(data[i + 1])) {
                    return false;
                }
            }
            i += type;
        }
        return true;
    }
    
    // 4 byte的数对前5位有要求，必须是11110xxx，所以mask需要5个1开头，11111000
    private int checkByte(int i) {
        if ((i & mask5) == 240) {
            return 4;
        } else if ((i & mask4) == 224) {
            return 3;
        } else if ((i & mask3) == 192) {
            return 2;
        } else if ((i & mask1) == 0) {
            return 1;
        }
        return -1;
    }
    
    // test if the num begins with 10xxxxxx
    private boolean beginWithOneAndZero(int i) {
        return (i & mask2) == 128;
    }
}
