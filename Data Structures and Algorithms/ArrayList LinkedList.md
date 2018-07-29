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




