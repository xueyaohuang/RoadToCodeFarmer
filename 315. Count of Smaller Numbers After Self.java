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
