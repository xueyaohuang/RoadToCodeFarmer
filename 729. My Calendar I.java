// 1. naive way
class MyCalendar {
    
    private List<int[]> events;

    public MyCalendar() {
        events = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] event : events) {
            if (start < event[1] && end > event[0]) {
                return false;
            }
        }
        events.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
 
 // 2. 类似merge interval，merge interval可以只对start进行sort，如果这里也只需对start sort，可以用treemap
 class MyCalendar {
    
    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer lower = calendar.floorKey(start);
        if (lower != null && calendar.get(lower) > start) {
            return false;
        }
        Integer upper = calendar.ceilingKey(start);
        if (upper != null && upper < end) {
            return false;
        }
        calendar.put(start, end);
        return true;
    } 
}

// 3. another treemap
class MyCalendar {
    
    private TreeMap<Integer, Integer> calendar;

    public MyCalendar() {
        calendar = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer lower = calendar.lowerKey(end);
        if (lower == null || calendar.get(lower) <= start) {
            calendar.put(start, end);
            return true;
        }
        return false;
    } 
}

// 4. BST, 几乎就是常规BST的翻版，加了一点其他特性
class MyCalendar {
    
    class Event {
        int start;
        int end;
        Event left;
        Event right;
        public Event(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    private Event root;

    public MyCalendar() {
        this.root = null;
    }
    
    public boolean book(int start, int end) {
        Event newRoot = insert(root, start, end);
        if (newRoot == null) {
            return false;
        }
        root = newRoot;
        return true;
    }
    
    private Event insert(Event root, int start, int end) {
        if (root == null) {
            return new Event(start, end);
        } else if (start >= root.end) { // go right
            Event right = insert(root.right, start, end);
            if (right == null) {
                return null;
            }
            root.right = right;
        } else if (end <= root.start) { // go left
            Event left = insert(root.left, start, end);
            if (left == null) {
                return null;
            }
            root.left = left;
        } else { // can not insert
            return null;
        }
        return root;
    }
}
