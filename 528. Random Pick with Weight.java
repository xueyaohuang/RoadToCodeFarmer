class Solution {

    int curStart;
    Node[] nodes;
    Node root;
    Random rand;

    
    public Solution(int[] w) {
        rand = new Random();
        int len = w.length;
        nodes = new Node[len];
        curStart = 0;
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i, curStart, curStart + w[i]);
            curStart += w[i];
        }
        root = buildTree(nodes, 0, len - 1);
    }
    
    private Node buildTree(Node[] nodes, int l, int r) {
        if (l > r) {
            return null;
        }
        int mid = (l + r) / 2;
        Node cur = nodes[mid];
        cur.left = buildTree(nodes, l, mid - 1);
        cur.right = buildTree(nodes, mid + 1, r);
        return cur;
    }
    
    public int pickIndex() {
        int num = rand.nextInt(curStart);
        return searchIdx(root, num);
    }
    
    private int searchIdx(Node root, int value) {
        Node cur = root;
        while (cur != null) {
            if (cur.start <= value && cur.end > value) {
                return cur.val;
            } else if (cur.start > value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return -1;
    }
    
    class Node {
        int val;
        int start;
        int end;
        Node left;
        Node right;
        public Node(int val, int start, int end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
 
 
 class Solution {
    
    TreeMap<Integer, Integer> map;
    Random rand;
    int accu;

    public Solution(int[] w) {
        map = new TreeMap<>();
        rand = new Random();
        accu = 0;
        for (int i = 0; i < w.length; i++) {
            map.put(accu, i);
            accu += w[i];
        }
    }
    
    public int pickIndex() {
        int idx = rand.nextInt(accu);
        int key = map.floorKey(idx);
        return map.get(key);
    }
}
class Solution {
    
    int[] sum;
    Random rand;

    public Solution(int[] w) {
        sum = new int[w.length];
        rand = new Random();
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
    }
    
    public int pickIndex() {
        int start = 0;
        int end = sum.length - 1;
        int target = rand.nextInt(sum[sum.length - 1]);
        /*
        ["Solution","pickIndex"]
        [[[3,14,1,8]],[]]
        
        [3, 17, 18, 26]
        */
        while (start < end) {
            int mid = (start + end) / 2;
            if (sum[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
