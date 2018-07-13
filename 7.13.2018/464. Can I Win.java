class Solution {
    Map<Integer, Boolean> map;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) {
            return false;
        }
        if (desiredTotal <= 0) {
            return true;
        }
        map = new HashMap<>();
        used = new boolean[1 + maxChoosableInteger];
        return helper(desiredTotal);
    }
    
    private boolean helper(int t) {
        if (t <= 0) {
            return false;
        } 
        int key = transform(used);
        if (!map.containsKey(key)) {
            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    if (!helper(t - i)) {
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }
    
    private int transform(boolean[] used) {
        int num = 0;
        for (boolean b : used){
            num <<= 1;
            if (b) {
                num |= 1;
            }
        }
        return num;
    }
    
    
}


class Solution {
     class Node{
        Node next = null;
        int val;
        Node(int val){
            this.val = val;
        }
        void insert(Node n){
            n.next = next;
            next = n;
        }
        void remove(){
            if (next != null){
                next = next.next;
            }
        }
    }
    byte[] map;
    public boolean canIWin(int max, int tgt) {
        int total = max * (max + 1) / 2;
        if (total < tgt){
            return false;
        }
        if (total == tgt){
            return max % 2 == 1;
        }
        if (max >= tgt){
            return true;
        }
        Node head = new Node(0);
        Node cur = head;
        for (int i = max; i >= 1; i--){
            cur.next = new Node(i);
            cur = cur.next;
        }
        map = new byte[1 << max];
        return dfs(head, tgt, 0);
    }
    
    boolean dfs(Node head, int tgt, int state){
        if (map[state] != 0){
            return map[state] > 0;
        }
        if (tgt <= 0){
            map[state] = -1;
            return false;
        }
        Node cur = head;
        while (cur.next != null){
            Node next = cur.next;
            cur.remove();
            if (!dfs(head, tgt - next.val, state | (1 << (next.val - 1)))){
                cur.insert(next);
                map[state] = 1;
                return true;
            }
            cur.insert(next);
            cur = next;
        }
        map[state] = -1;
        return false;
    }
}
