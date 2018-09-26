class NumArray {
    
    FenwickTree sums;
    int[] nums;

    //init O(nlogn)
    public NumArray(int[] nums) {
        int len = nums.length;
        sums = new FenwickTree(len);
        this.nums = nums;
        for (int i = 0; i < len; i++) {
            sums.update(i + 1, nums[i]);
        }
    }
    
    public void update(int i, int val) {
        sums.update(i + 1, val - nums[i]);
        nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        int sumEndJ = sums.query(j + 1);
        int sumEndI_1 = sums.query(i);
        return sumEndJ - sumEndI_1;
    }
    
    class FenwickTree {
        int[] sums;
        
        public FenwickTree(int n) {
            sums = new int[n + 1];
        }
        
        // O(lgn)
        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += i & -i; // 找上一个节点
            }
        }
        
        // O(lgn)
        public int query(int i) {
            int sumEndHere = 0;
            while (i > 0) {
                sumEndHere += sums[i];
                i -= i & -i; // 找上一个节点，注意这里是-=，跟update的+=不一样
            }
            return sumEndHere;
        }
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
