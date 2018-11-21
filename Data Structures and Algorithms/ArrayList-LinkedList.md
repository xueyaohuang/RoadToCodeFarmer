# ArrayList and LinkedList

## ArrayList  

1. Inherits AbstractList class and implements List interface.

2. Randomly access the list.  
3. Need a wrapper class for primitive types, e.g. Integer for int.
4. Constructor:
    * ArrayList(): This constructor is used to build an empty array list
    * ArrayList(Collection c): This constructor is used to build an array list initialized with the elements from collection c
5. [Useful methods](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#ArrayList-java.util.Collection-)
    * iterator(), clone(), clear(), forEach()
    * remove(int index), remove(Object o), 	set(int index, E element)
    * sort(Comparator<? super E> c)
    * subList(int fromIndex, int toIndex), 	toArray()

## ArrayList vs Array

1. Array: Simple fixed sized arrays. ArrayList : Dynamic sized arrays.

2. Array can contain both primitive data types as well as objects of a class depending on the definition of the array. However, ArrayList only supports object entries, not the primitive data types.
Note: When we do arraylist.add(1); : it converts the primitive int data type into an Integer object, auto-boxing. 
3. Since ArrayList can’t be created for primitive data types, members of ArrayList are always references to objects at different memory locations (See this for details). Therefore in ArrayList, the actual objects are never stored at contiguous locations. References of the actual objects are stored at contiguous locations.
In array, it depends whether the arrays is of primitive type or object type. In case of primitive types, actual values are contiguous locations, but in case of objects, allocation is similar to ArrayList.

## LinkedList

1. Linked List are linear data structures where the elements are not stored in contiguous locations and every element is a separate object with a data part and address part. 

2. The elements are linked using pointers and addresses. Each element is known as a node. 

3. Due to the dynamicity and ease of insertions and deletions, they are preferred over the arrays. 

4. It also has few disadvantages like the nodes cannot be accessed directly instead we need to start from the head and follow through the link to reach to a node we wish to access.

5. To store the elements in a linked list we use a doubly linked list which provides a linear data structures.

6. Constructors 与 ArrayList类似

7. LinkedList有很多看似一样的methods，因为它要implement不同的interface。比如LinkedList为什么既有add()，又有addLast(): LinkedList class implements both Deque and Queue interface. It inherits add(E) method from Queue, and addLast(E) method from Deque. As the javadoc states, addLast and add are equivalent.


8. The difference between offer and add is explained by these two excerpts from the javadocs:  

From the Collection interface:  

> If a collection refuses to add a particular element for any reason other than that it already contains the element, it must throw an exception (rather than returning false). This preserves the invariant that a collection always contains the specified element after this call returns.

From the Queue interface

> When using queues that may impose insertion restrictions (for example capacity bounds), method offer is generally preferable to method Collection.add(E), which can fail to insert an element only by throwing an exception.

PriorityQueue is a Queue implementation that does not impose any insertion restrictions. Therefore the add and offer methods have the same semantics.

By contrast, ArrayBlockingQueue is an implementation in which offer and add behave differently, depending on how the queue was instantiated.

```ListNode```
```
public class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class DListNode {
    int val;
    DListNode prev;
    DListNode next;
    DListNode(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}
```

### LinkedList 基本操作

* 单向链表reverse
```
// iterative method 
public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}

// recursive method 
public ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode next = head.next;
    ListNode newHead = reverse(next);
    next.next = head;
    head.next = null;
    return newHead;
}

```
* 双向链表reverse
```
public DListNode reverse(DListNode head) {
    DListNode curr = null;
    while (head != null) {
        curr = head;
        head = curr.next;
        curr.next = curr.prev;
        curr.prev = head;
    }
    return curr;
}
```
* 单向链表删除一个node，但是只知道要删除的node。Since we couldn't enter the preceding node, we can not delete the given node. We can just copy the next node to the given node and delete the next one.
```
 public void deleteNode(ListNode node) {
     if (node != null && node.next != null) {
         ListNode next = node.next;
         node.val = next.val;
         node.next = next.next;
         next.next = null;
     }
 }
```
* 双向链表删除一个node
```
 public void deleteNode(DListNode node) {
     DListNode post = node.post;
     DListNode pre = node.pre;
     pre.next = post;
     post.prev = pre; 
 }
```

* 链表求长度
```
public int getLength(ListNode head) {
    int len = 0;
    ListNode node = head; // 最好不要改变输入
    while (node != null) {
        node = node.next;
        len++;
    }
    return len;
}
```

* 找链表中点
```
 public ListNode findMiddle(ListNode head) {
     ListNode slow = head;
     ListNode fast = head.next;
     while (fast != null && fast.next != null) {
         slow = slow.next;
         fast = fast.next.next;
     }
     return slow;
 }
```

* 链表判断是否有环
```
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head.next; // fast也可以从head开始
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
```

* 找到链表环的起始点
```
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ListNode node = head;
                while (node != slow) {
                    node = node.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
```

* 两个链表找交点
```
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next; // 注意是判断nodeA == null而不是nodeA.next == null
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }
}
// 方法2：求出两个链表的长度，长的链表先走一段，使两个链表的头对齐。然后开始一起往前走，知道相等的时候停下返回。
```

* merge two sorted list
```
private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
        return dummy.next;
    }
    
    // recursice way of merge two sorted list
    private ListNode merge(ListNode l1, ListNode l2){
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else{
            l2.next = merge(l2.next,l1);
            return l2;
        }
    }
```

* 当链表的 head 有可能变化（被修改或者被删除）时，使用 dummy node ，最终返回 dummy.next 即新的链表。

* fast，slow快慢指针。在单链表中让快指针每次向前移动2，慢指针则每次向前移动1，快慢两个指针都从链表头开始遍历。快慢指针在链表相关问题中主要有两个应用：
  * 快速找出未知长度单链表的中间节点 设置两个指针 *fast、*slow 都指向单链表的头节点，其中*fast的移动速度是*slow的2倍，当*fast指向末尾节点的时候，slow正好就在中间了。
  * 判断单链表是否有环 利用快慢指针的原理，同样设置两个指针 *fast、*slow 都指向单链表的头节点，其中 *fast的移动速度是*slow的2倍。如果 *fast = NULL，说明该单链表 以 NULL结尾，不是循环链表；如果 *fast = *slow，则快指针追上慢指针，说明该链表是循环链表。



## ArrayList vs LinkedList

[ArrayList vs LinkedList Excellent comparison](https://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist)

1. Insertions are easy and fast in LinkedList as compared to ArrayList because there is no
risk of resizing array and copying content to new array if array gets full which makes
adding into ArrayList of O(n) in worst case, while adding is O(1) operation in LinkedList
in Java. 

2. ArrayList also needs to be update its index if you insert something anywhere except
at the end of array.
3. Removal also better in LinkedList than ArrayList due to same reasons as insertion.
4. LinkedList has more memory overhead than ArrayList because in ArrayList each index only
holds actual object (data) but in case of LinkedList each node holds both data and address
of next and previous node.
5. Both LinkedList and ArrayList require O(n) time to find if an element is present or not. However we can do Binary Search on ArrayList if it is sorted and therefore can search in O(Log n) time.




