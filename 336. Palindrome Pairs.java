class Solution {
    class TrieNode{
        TrieNode[] next;
        int val;
        List<Integer> list;
        TrieNode(){
            next=new TrieNode[26];
            val=-1;
            list=new LinkedList();
        }
    }
    
    List<List<Integer>> res=new LinkedList();
    public List<List<Integer>> palindromePairs(String[] words) {
        TrieNode root=new TrieNode();
        for(int i=0;i<words.length;i++) addword(root,words[i],i);
        for(int i=0;i<words.length;i++) search(root,words[i],i);
        
        return res;
    }
    private void addword(TrieNode root, String word,int index){
        for(int i=word.length()-1;i>=0;i--){            
            if(isP(word,0,i)) root.list.add(index);
            char c=word.charAt(i);
             if(root.next[c-'a']==null) root.next[c-'a']=new TrieNode();   
            root=root.next[c-'a'];
        }
        root.val=index;
        root.list.add(index);
    }
    private void search(TrieNode root,String word,int index){
        for(int i=0;i<word.length();i++){
            if(root.val>=0 && root.val!=index && isP(word,i,word.length()-1)) res.add(Arrays.asList(index,root.val));
            root=root.next[word.charAt(i)-'a'];
            if(root==null) return;
        }
        for(int j:root.list){
            if(j!=index) res.add(Arrays.asList(index,j));
        }
        
    }
    private boolean isP(String word, int i,int j){
        while(i<j){
            if(word.charAt(i++)!=word.charAt(j--)) return false;
        }
        return true;
    }  
}
