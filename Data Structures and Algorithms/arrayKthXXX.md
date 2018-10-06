# 给一个array，找到第k小（大）的xxx

1. binary search  
典型题目：378 Kth Smallest Element in a Sorted Matrix， 719 Find K-th Smallest Pair Distance  
通常需要sort一遍array，然后确定要找的值的范围，然后在这个范围内用binary search。对于一个mid，若数的个数小于k，end=mid，反之start=mid+1.
一个主要的问题是，怎么数出小于mid的个数。

2. heap  
典型题目：373 Find K Pairs with Smallest Sums， 378 Kth Smallest Element in a Sorted Matrix  
