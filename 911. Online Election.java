// constructor TopVotedCandidate(int[] persons, int[] times)	is O(nlogn), and for q(int t) is O(logn).
class TopVotedCandidate {
    
    TreeMap<Integer, Integer> map;

    public TopVotedCandidate(int[] persons, int[] times) {
        map = new TreeMap<>();
        int max = -1, winner = -1;
        int[] count = new int[persons.length + 1];
        for (int i = 0; i < persons.length; i++) {
            count[persons[i]]++;
            if (max <= count[persons[i]]) {
                max = count[persons[i]];
                winner = persons[i];
            }
            map.put(times[i], winner);
        }
    }
    
    public int q(int t) {
        int nearestTime = map.floorKey(t);
        return map.get(nearestTime);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
 
 // constructor TopVotedCandidate(int[] persons, int[] times)	is O(nlogn), and for q(int t) is O(logn).
 // optimize: 只有winner换人时才放入treemap
 class TopVotedCandidate {
    
    TreeMap<Integer, Integer> map;

    public TopVotedCandidate(int[] persons, int[] times) {
        map = new TreeMap<>();
        int max = -1;
        int[] count = new int[persons.length + 1];
        for (int i = 0; i < persons.length; i++) {
            count[persons[i]]++;
            if (max <= count[persons[i]]) {
                max = count[persons[i]];
                map.put(times[i], persons[i]);
            }
        }
    }
    
    public int q(int t) {
        int nearestTime = map.floorKey(t);
        return map.get(nearestTime);
    }
}

// best: binarysearch for times. Constructor O(n), q(int t) is O(logn).
class TopVotedCandidate {
    
    int[] times;
    Map<Integer, Integer> map;
    
    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        this.map = new HashMap<>();
        int[] count = new int[persons.length + 1];
        int max = -1, winner = -1;
        for (int i = 0; i < persons.length; i++) {
            count[persons[i]]++;
            if (max <= count[persons[i]]) {
                max = count[persons[i]];
                winner = persons[i];
            }
            map.put(times[i], winner);
        }
    }
    
    public int q(int t) {
        int idx = Arrays.binarySearch(times, t); // search for the time slot.
        // In Java, Arrays.binarySearch will return the index of the search key, 
        // if it is contained in the array; otherwise, (-(insertion point) - 1).
        // So if i < 0, -i - 1 will be the insertion point, and -i - 2 is the floor key.
        return map.get(times[idx < 0 ? -idx - 2 : idx]); 
    }
}
