/* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int idx = 0;
        int len = 0;
        char[] temp = new char[4];
        while (idx < n && (len = read4(temp)) > 0) {
            for (int j = 0; j < len && idx < n;) {
                buf[idx++] = temp[j++];
            }
        }
        return idx;
    }
}
