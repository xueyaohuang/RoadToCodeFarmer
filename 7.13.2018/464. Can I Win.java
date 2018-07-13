class Solution {
  
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger <= 0 || desiredTotal <= 0)
            return true;
        int maxPossibleSum = maxChoosableInteger*(maxChoosableInteger+1)/2;
        if(maxPossibleSum<desiredTotal)
            return false;
        int[] chosen = new int[maxChoosableInteger+1];
        return helper(desiredTotal, chosen, new HashMap<String,Boolean>());
    }
    
    private boolean helper(int desiredTotal, int[] chosen,Map<String,Boolean> map){
        if(desiredTotal<=0)
            return false;
        String choseStr = Arrays.toString(chosen);  
        if(!map.containsKey(choseStr)){
             for(int i=1;i<chosen.length; i++){
                if(chosen[i]==1)
                    continue;
                chosen[i] =1;
                if(!helper(desiredTotal-i,chosen,map)){ 
                    chosen[i]=0;        
                    map.put(choseStr,true);
                    return true;
                }
                chosen[i]=0;
             }
             map.put(choseStr,false);
        }
       
        return map.get(choseStr);
    }
}
