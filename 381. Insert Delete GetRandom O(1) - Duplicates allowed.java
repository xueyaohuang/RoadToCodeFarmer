public class RandomizedCollection {

    ArrayList<Integer> result;
    HashMap<Integer, LinkedHashSet<Integer>> map;
    
    public RandomizedCollection() {
        result = new ArrayList<Integer>();
        map = new HashMap<Integer, LinkedHashSet<Integer>>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        // Add item to map if it doesn't already exist.
        boolean alreadyExists = map.containsKey(val);
        if(!alreadyExists) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(result.size());
        result.add(val);
        return !alreadyExists;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) {
            return false;
        }
        // Get arbitary index of the ArrayList that contains val
        // 用LinkedHashSet是因为它的iterator()函数是O(1)的时间复杂度，一般的set是O(h/n), h是set的capacity。
        LinkedHashSet<Integer> valSet = map.get(val);
        int indexToReplace = valSet.iterator().next();
        
        // Obtain the set of the number in the last place of the ArrayList
        int numAtLastPlace = result.get(result.size() - 1);
        LinkedHashSet<Integer> replaceWith = map.get(numAtLastPlace);
        
        // Replace val at arbitary index with very last number
        result.set(indexToReplace, numAtLastPlace);
        
        // Remove appropriate index
        valSet.remove(indexToReplace);
        
        // Don't change set if we were replacing the removed item with the same number
        if(indexToReplace != result.size() - 1) {
            replaceWith.remove(result.size() - 1);
            replaceWith.add(indexToReplace);
        }
        result.remove(result.size() - 1);
        
        // Remove map entry if set is now empty, then return
        if(valSet.isEmpty()) {
            map.remove(val);
        }
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        // Get linearly random item
        return result.get((int)(Math.random() * result.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
