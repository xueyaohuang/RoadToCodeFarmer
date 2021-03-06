class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        List<Integer> number = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
            number.add(i);
        }
        number.add(n);
        k--; // 变成从0开始数的，第0个，第1个，....., 第k-1个
        for (int i = 1; i <= n; i++) {
            int idx = k / factorial[n - i];
            sb.append(number.get(idx));
            number.remove(idx);
            k -= idx * factorial[n - i];
        }
        return sb.toString();
    }
}

// 0 1 2 3
// 1 1 2 6
    
// 1234
// 1243
// 1324
// 1342
// 1423
// 1432
