class Solution {
    public int kthFactor(int n, int k) {
        int max = (int)Math.sqrt(n);
        List<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (int i = 2; i <= max; i++) {
            if (n % i == 0) {
                factors.add(i);
            }
        }
        int size = factors.size();
        if (max * max != n) {
            factors.add(n / factors.get(size - 1));
        }
        for (int j = size - 2; j >= 0; j--) {
            factors.add(n / factors.get(j));
        }
        if (factors.size() >= k) {
            return factors.get(k - 1);
        }
        return -1;
    }
}
