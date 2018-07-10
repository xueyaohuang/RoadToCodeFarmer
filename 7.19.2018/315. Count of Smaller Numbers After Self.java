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
    public List<Integer> countSmaller(int[] nums) {
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        LinkedList<Integer> list = new LinkedList<>();
        TreeNode head = new TreeNode(nums[nums.length - 1]);
        list.add(0);
        for (int i = nums.length - 2; i >= 0; i--) {
            int count = insert(head, nums[i]);
            list.addFirst(count);
        }
        return list;
    }
    
    private int insert(TreeNode root, int num) {
        int count = 0;
        while (true) {
            if (num <= root.val) {
                root.count++;
                if (root.left == null) {
                    root.left = new TreeNode(num);
                    break;
                } else {
                    root = root.left;
                }
            } else {
                count += root.count;
                if (root.right == null) {
                    root.right = new TreeNode(num);
                    break;
                } else {
                    root = root.right;
                }
            }
        }    
        return count;
    }
    
    private static class TreeNode {
        int val;
        int count;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            this.count = 1;
        }
    }
}
