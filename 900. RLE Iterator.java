class RLEIterator {
    
    int[] arr;
    int idx;
    int num;

    public RLEIterator(int[] A) {
        arr = A;
        idx = 0;
        num = A[0];
    }
    
    public int next(int n) {
        while (n > 0) {
            if (n > num) {
                n -= num;
                idx += 2;
                if (idx < arr.length) {
                    num = arr[idx];
                }
            } else {
                num -= n;
                n = 0;
            }
            if (idx >= arr.length) {
                break;
            }
        }
        return idx >= arr.length ? -1 : arr[idx + 1];
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(A);
 * int param_1 = obj.next(n);
 */
