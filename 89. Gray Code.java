class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }
}
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 0; i < n; i++) {
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) | (1 << i));
            }
        }
        return res;
    }
}
// 找规律，重点在res.add(res.get(j) | (1 << i));
// 从n=i到n=i+1，一直往res中的每个数的最高位或1.
// n = 0：0
// n = 1: 0, 1
// n = 2: 0, 1, 11, 10
// n = 3: 0, 1, 11, 10, 110, 111, 101, 100
// n = 4: 0, 1, 11, 10, 110, 111, 101, 100, 1100, 1101, 1111, 1110, 1010, 1011, 1001, 1000
