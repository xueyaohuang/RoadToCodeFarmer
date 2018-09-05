import java.util.*;
            
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class Main {
    
    private List<Integer> printTreeNode(List<TreeNode> list) {
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        for (TreeNode node : list) {
            res.add(node.val);
        }
        return res;
    }
    
    public static void main(String[] args) {
        
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(4);
        
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.left = t5;
        t3.right = t6;
        t5.left = t7;
        
        Main m = new Main();
        
        List<TreeNode> ans = m.findDupSubTree(t1);
        List<Integer> res = m.printTreeNode(ans);
        
        System.out.println(res);
    }
}
