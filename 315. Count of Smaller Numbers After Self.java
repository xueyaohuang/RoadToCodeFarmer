// https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76674/3-Ways-(Segment-Tree-Binary-Indexed-Tree-Merge-Sort)-clean-Java-code

// https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76611/Short-Java-Binary-Index-Tree-BEAT-97.33-With-Detailed-Explanation

// https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76576/My-simple-AC-Java-Binary-Search-code
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
        	return res;
        }
        for (int i = 0; i < nums.length - 1; i++) {
        	res.add(countAfter(nums, i));
        }
        res.add(0);
        return res;
    }
    private int countAfter(int[] nums, int i) {
    	int len = nums.length;
    	int count = 0;
    	for (int j = i + 1; j < len; j++) {
            if (nums[i] > nums[j]) {
            	count++;
            }
    	}
    	return count;
    }
}

class Solution {
    
    private class TreeNode {
        
        private int count; // count how many nodes are there in the left subtree including the current node itself
        private int val;
        private TreeNode left;
        private TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }
    }
    
    private int insert(TreeNode root, int val) {
        int count = 0;
        while (true) {
            if (val <= root.val) {
                root.count++; 
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    break;
                }
                else {
                    root = root.left;
                }
            }
            else {
                count += root.count;
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    break;
                }
                else {
                    root = root.right;
                }
            }
        }
        return count;
    }
    
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<Integer>();
        }
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode root = new TreeNode(nums[nums.length - 1]);
        res.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            res.addFirst(insert(root, nums[i]));
        }
        return res;
    }
}

class Solution {
    
    class FenwichTree {
        int[] sums;

        public FenwichTree(int n) {
            sums = new int[n + 1];
        }

        public void update(int i, int delta) {
            while (i < sums.length) {
                sums[i] += delta;
                i += i & -i;
            }
        }

        public int query(int i) {
            int sumEndHere = 0;
            while (i > 0) {
                sumEndHere += sums[i];
                i -= i & -i;
            }
            return sumEndHere;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        
        int len = nums.length;
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        
        
        int rank = 0;
        for (int i = 0; i < len; i++) {
            if (i == 0 || copy[i] != copy[i - 1]) {
                rank++;
                map.put(copy[i], rank);
            }
        }
        
        FenwichTree ft = new FenwichTree(rank);
        for (int i = len - 1; i >= 0; i--) {
            res.add(ft.query(map.get(nums[i]) - 1));
            ft.update(map.get(nums[i]), 1);
        }
        Collections.reverse(res);
        return res;
    }
}
