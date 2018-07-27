class Solution {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length){
            return false;
        }
        Map<String, String> map = new HashMap<>();
        for (String[] pair : pairs){
            String node1 = findBoss(map, pair[0]);
            String node2 = findBoss(map, pair[1]);
            if (node1 == null || node2 == null || !node1.equals(node2)){
                map.put(node1, node2);
            }
        }
        
        for (int i = 0; i < words1.length; i++){
            String node1 = findBoss(map, words1[i]);
            String node2 = findBoss(map, words2[i]);
            if (node1 == null || node2 == null || !node1.equals(node2)){
                return false;
            }
        }
        return true;
    }
    
    private String findBoss(Map<String, String> map, String word){
        String boss = map.get(word);
        while (boss!= null){
            word = boss;
            boss = map.get(word);
        }
        return word;
    }
}
