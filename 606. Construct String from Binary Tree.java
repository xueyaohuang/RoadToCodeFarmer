class Solution {
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preOrder(t, sb);
        return sb.toString();
    }
    
    private void preOrder(TreeNode t, StringBuilder sb) {
        if (t == null) {
            return;
        }
        sb.append(t.val);
        if (t.left == null && t.right == null) {
            return;
        }
        sb.append("(");
        preOrder(t.left, sb);
        sb.append(")");
        if (t.right != null) {
            sb.append("(");
            preOrder(t.right, sb);
            sb.append(")");
        }
    }
}


class Solution {
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        preOrder(t, sb);
        return sb.toString();
    }
    private void preOrder(TreeNode t, StringBuilder sb) {
        if (t == null) return;
        sb.append(t.val);
        if (t.left != null || t.right != null) {
            sb.append("(");
            preOrder(t.left, sb);
            sb.append(")");
        }
        if (t.right != null) {
            sb.append("(");
            preOrder(t.right, sb);
            sb.append(")");
        }
    }
}
