/* The read4 API is defined in the parent class Reader4.
      int read4(char[]  ); */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    // 可能上一次读的很少，但是每次最少读4个，上次读完了。所以要把上次读的内容存起来。
    char[] readBuffer = new char[4];
    int prevReadSize = 0;
    int prevWriteSize = 0;
    public int read(char[] buf, int n) {
        int len = 0;
        while (len < n) {
            // prevWriteSize < prevReadSize表示上一次读的还没写完
            if (prevWriteSize < prevReadSize) {
                buf[len] = readBuffer[prevWriteSize];
                len++;
                prevWriteSize++;
            } else { // 重新读4个char到readBuffer
                prevWriteSize = 0;
                prevReadSize = read4(readBuffer);
                if (prevReadSize == 0) {
                    break;
                }
            }
        }
        return len;
    }
}
