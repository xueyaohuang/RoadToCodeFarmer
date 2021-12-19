// Java Factory method pattern
abstract class Node {
    public abstract int evaluate();
    
    public static Node from(String value) {
        switch(value) {
            case "+":
                return new AdditionNode();
            case "-":
                return new SubtractionNode();
            case "*":
                return new MultiplicationNode();
            case "/":
                return new DivisionNode();
            default:
                return new NumericalNode(value);
        }
    }
};

abstract class OperatorNode extends Node {
    protected Node left;
    protected Node right;
	
    public void setLeft(Node left) {
        this.left = left;
    }
	
    public void setRight(Node right) {
        this.right = right;
    }
}

class AdditionNode extends OperatorNode {
    public int evaluate() {
        return left.evaluate() + right.evaluate();
    }
}

class SubtractionNode extends OperatorNode {
    public int evaluate() {
        return left.evaluate() - right.evaluate();
    }
}

class MultiplicationNode extends OperatorNode {
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}

class DivisionNode extends OperatorNode {
    public int evaluate() {
        return left.evaluate() / right.evaluate();
    }
}

class NumericalNode extends Node {
    private String value;
	
    public NumericalNode(String v) {
        value = v;
    }
	
    public int evaluate() {
        return Integer.valueOf(value);
    }
}

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Stack<Node> st = new Stack<>();
		
        for (String s : postfix) {
            Node n = Node.from(s);
            if (n instanceof NumericalNode) {
                st.push(n);
            } else if (n instanceof OperatorNode) {
                OperatorNode op = (OperatorNode) n;
                op.setRight(st.pop());
                op.setLeft(st.pop());
                st.push(op);
            } else {
                throw new IllegalStateException("node should be instance of NumericalNode or OperatorNode");
            }
        }
		
        return st.pop();
    }
};

/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

abstract class Node {
    public abstract int evaluate();
    // define your fields here
};

class OperandNode extends Node {
    int val;
    public OperandNode(int val) {
        this.val = val;
    }
    
    public int evaluate() {
        return val;
    }
}

abstract class OperatorNode extends Node {
    Node left;
    Node right;
    public OperatorNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}

class AdditionNode extends OperatorNode {
    public AdditionNode(Node left, Node right) {
        super(left, right);
    }
    
    public int evaluate() {
        return this.left.evaluate() + this.right.evaluate();
    }
}

class SubtractionNode extends OperatorNode {
    public SubtractionNode(Node left, Node right) {
        super(left, right);
    }
    
    public int evaluate() {
        return this.left.evaluate() - this.right.evaluate();
    }
}

class MultiplicationNode extends OperatorNode {
    public MultiplicationNode(Node left, Node right) {
        super(left, right);
    }
    
    public int evaluate() {
        return this.left.evaluate() * this.right.evaluate();
    }
}

class DivisionNode extends OperatorNode {
    public DivisionNode(Node left, Node right) {
        super(left, right);
    }
    
    public int evaluate() {
        return this.left.evaluate() / this.right.evaluate();
    }
}

/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    Node buildTree(String[] postfix) {
        Deque<Node> stack = new ArrayDeque<>();
        for (String s : postfix) {
            if (Character.isDigit(s.charAt(0))) {
                stack.push(new OperandNode(Integer.parseInt(s)));
            } else {
                // 注意先pop出right
                Node right = stack.pop();
                Node left = stack.pop();
                stack.push(buildNode(left, right, s));
            }
        }
        return stack.pop();
    }
    
    private Node buildNode(Node left, Node right, String s) {
        switch (s) {
            case "+":
                return new AdditionNode(left, right);
            case "-":
                return new SubtractionNode(left, right);
            case "*":
                return new MultiplicationNode(left, right);
            case "/":
                return new DivisionNode(left, right);
            default:
                return null;
        }
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */
