public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // use level order to code root.val to string, save the null as a single character like "#"
        if(root == null){
            return null;
        }
        
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> bfs = new LinkedList<TreeNode>();
        
        bfs.offer(root);
        sb.append((char)('0' + root.val));
        while(!bfs.isEmpty()){
            TreeNode node = bfs.poll();
            if(node.left != null){
                bfs.offer(node.left);
                sb.append((char)('0' + node.left.val));
            }
            else{
                sb.append('#');
            }
            if(node.right != null){
                bfs.offer(node.right);
                sb.append((char)('0' + node.right.val));
            }
            else{
                sb.append('#');
            }
        }
        while(sb.charAt(sb.length() - 1) == '#'){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null){
            return null;
        }
        TreeNode root = new TreeNode(data.charAt(0) - '0');
        // use queue to store the nodes in the previous layer
        int pointer = 1;
        Queue<TreeNode> bfs = new LinkedList<TreeNode>();
        bfs.offer(root);
        while(pointer < data.length()){
            TreeNode node = bfs.poll();
            if(data.charAt(pointer) != '#'){
                node.left = new TreeNode(data.charAt(pointer) - '0');
                bfs.offer(node.left);
            }
            pointer ++;
            if(pointer >= data.length()){
                break;
            }
            if(data.charAt(pointer) != '#'){
                node.right = new TreeNode(data.charAt(pointer) - '0');
                bfs.offer(node.right);
            }
            pointer ++;
        }
        return root;
    }
}

public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}
