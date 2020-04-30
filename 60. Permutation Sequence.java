class Solution {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        List<Integer> number = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        for (int i = 1; i <= n; i++) {
            number.add(i);
        }
        k--; // 变成从0开始数的，第0个，第1个，....., 第k-1个
        for (int i = 1; i <= n; i++) {
            int index = k / factorial[n - i];
            sb.append(Integer.toString(number.get(index)));
            number.remove(index);
            k -= index * factorial[n - i];
        }
        return sb.toString();
    }
}
