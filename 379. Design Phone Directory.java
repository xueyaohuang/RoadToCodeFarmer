class PhoneDirectory {
    
    Set<Integer> set;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (set.isEmpty()) {
            return -1;
        }
        int temp = set.iterator().next();
        set.remove(temp);
        return temp;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
 
// BitSet is better
public class PhoneDirectory {

    BitSet bitset;
    int max; // max limit allowed
    int smallestFreeIndex; // current smallest index of the free bit

    public PhoneDirectory(int maxNumbers) {
        this.bitset = new BitSet(maxNumbers);
        this.max = maxNumbers;
    }

    public int get() {
        // handle bitset fully allocated
        if(smallestFreeIndex == max) {
            return -1;
        }
        int num = smallestFreeIndex;
        bitset.set(smallestFreeIndex);
        //Only scan for the next free bit, from the previously known smallest free index
        smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
        return num;
    }

    public boolean check(int number) {
        return !bitset.get(number);
    }

    public void release(int number) {
        //handle release of unallocated ones
        if(!bitset.get(number)) {
            return;
        }
        bitset.clear(number);
        if(number < smallestFreeIndex) {
            smallestFreeIndex = number;
        }
    }
}


