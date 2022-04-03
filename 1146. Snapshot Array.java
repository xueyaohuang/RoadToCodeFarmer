class SnapshotArray {
    
    TreeMap<Integer, Map<Integer, Integer>> map;
    Map<Integer, Integer> nums;
    int id;

    public SnapshotArray(int length) {
        map = new TreeMap<>();
        nums = new HashMap<>();
    }
    
    public void set(int index, int val) {
        nums.put(index, val);
        if (!map.containsKey(id)) {
            map.put(id, new HashMap<>(nums));
        } else {
            map.get(id).put(index, val);
        }
    }
    
    public int snap() {
        return id++;
    }
    
    public int get(int index, int snap_id) {
        Integer latestChangedId = map.floorKey(snap_id);
        if (latestChangedId == null) {
            return 0;
        }
        Map<Integer, Integer> snapshot = map.get(latestChangedId);
        if (!snapshot.containsKey(index)) {
            return 0;
        }
        return snapshot.get(index);
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
