class BrowserHistory {
    
    List<String> history;
    int cur;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        cur = 0;
    }
    
    public void visit(String url) {
        for (int i = history.size() - 1; i > cur; i--) {
            history.remove(i);
        }
        history.add(url);
        cur++;
    }
    
    public String back(int steps) {
        if (steps > cur) {
            cur = 0;
        } else {
            cur -= steps;
        }
        return history.get(cur);
    }
    
    public String forward(int steps) {
        if (cur + steps >= history.size()) {
            cur = history.size() - 1;
        } else {
            cur += steps;
        }
        return history.get(cur);
    }
}

class BrowserHistory {
    
    Stack<String> history = new Stack<>();
    Stack<String> future = new Stack<>();
    public BrowserHistory(String homepage) {
        history.add(homepage);
        future.clear();
    }
    
    public void visit(String url) {
        history.add(url);
        future.clear();
    }
    
    public String back(int steps) {
        while (steps > 0 && history.size() > 1) {
            future.add(history.pop());
            steps--;
        }
        return history.peek();
    }
    
    public String forward(int steps) {
        while (steps > 0 && future.size() > 0) {
            history.add(future.pop());
            steps--;
        }
        return history.peek();
    }
}

class BrowserHistory {
    
    public class Node{
        String url;
        Node next, prev;
        public Node(String url) {
            this.url = url;
            next = null;
            prev = null;
        }
    }
    
    Node head, curr;
    public BrowserHistory(String homepage) {
        head = new Node(homepage);
        curr = head;
    }
    
    public void visit(String url) {
        Node node = new Node(url);
        curr.next = node;
        node.prev = curr;
        curr = node;
    }
    
    public String back(int steps) {
        while (curr.prev != null && steps-- > 0) {
            curr = curr.prev;
        }
        return curr.url;
    }
    
    public String forward(int steps) {
        while (curr.next != null && steps-- > 0) {
            curr = curr.next;
        }
        return curr.url;
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
