class AutocompleteSystem {
    //TrieNode class
    class TrieNode {
        public Map<Character, TrieNode> children;
        public Map<String, Integer> counts;
        public TrieNode() {
            children = new HashMap<>();
            counts = new HashMap<>();
        }
    }
    public class Pair {
        String s;
        int c;
        public Pair(String s, int c) {
            this.s = s;
            this.c = c;
        }
    }
    TrieNode root;
	//we'll move cur
    TrieNode cur;
    String prefix;
    
    public void insert(String s, int count) {
        TrieNode node = root;
        for (char c: s.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            node.counts.put(s, node.counts.getOrDefault(s, 0) + count);
        }
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        //empty the sentences in root
        root = new TrieNode();
        prefix = "";
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
        cur = root;
    }
    
    public List<String> input(char c) {
        //everytime we input a character, check if its' #: if so, we add current sentences to the Trie, reset cur to the root and return empty list
        if (c == '#') {
            insert(prefix, 1);
            //reset cur!!
            cur = root;
            prefix = "";
            return new ArrayList<>();
        }

        // prefix += c; 要在if (cur == null) 之前，因为即使没有这个prefix，也要一直更新prefix，最终记录下来
        prefix += c;
        if (cur == null) {
            return new ArrayList<>();
        }
        //if the current node doesn't contain c，set cur to NULLand return empty list
        if (!cur.children.containsKey(c)) {
            cur = null;
            return new ArrayList<>();
        }
		//if the current node contains c, update cur to the node that has c
        cur = cur.children.get(c);
        //use heap to make sure we output top 3 sentences
		//p.s. need to rewrite the comparator
        PriorityQueue<Pair> heap = new PriorityQueue<>((o1, o2) -> o1.c == o2.c? o1.s.compareTo(o2.s): o2.c - o1.c);
        // PriorityQueue<Pair> heap = new PriorityQueue<>(new Comparator<Pair>() {
        //     @Override
        //     public int compare(Pair o1, Pair o2) {
        //         if (o1.c == o2.c) {
        //             return o1.s.compareTo(o2.s);
        //         } else {
        //             return o2.c - o1.c;
        //         }
        //     }
        // });
        for (String str: cur.counts.keySet()) {
            heap.add(new Pair(str, cur.counts.get(str)));
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 3 && !heap.isEmpty(); i++) {
            res.add(heap.poll().s);
        }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
