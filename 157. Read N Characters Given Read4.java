/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int len = 0;
        int curLen = 4;
        while (len < n && curLen == 4) {
            char[] curRead = new char[4];
            curLen = read4(curRead);
            for (int i = 0; i < curLen && len < n; i++) {
                buf[len] = curRead[i];
                len++;
            }
        }
        return len;
    }
}
