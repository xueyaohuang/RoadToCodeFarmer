二分模板一共有2个，分别适用于不同情况。

版本1
当区间[l, r]的更新操作是r = mid; l = mid + 1;时，计算mid时不需要加1。

C++ 代码模板：
int bsearch_1(int l, int r)
{
    while (l < r)
    {
        int mid = l + r >> 1;
        if (check(mid)) r = mid;
        else l = mid + 1;
    }
    return l;
}

版本2
当区间[l, r]的更新操作是r = mid - 1; l = mid;时，计算mid时需要加1。

C++ 代码模板：
int bsearch_2(int l, int r)
{
    while (l < r)
    {
        int mid = l + r + 1>> 1;
        if (check(mid)) l = mid;
        else r = mid - 1;
    }
    return l;
}

这两种版本都没在while里面return，即使可以return，也故意把可以return的情况并入其中一个分支，最后chenk start。比如lc
702. Search in a Sorted Array of Unknown Size， 在while内部return和没有在while内部return的两种写法。
当在while里面有return时，退出条件可能是while (l < r)

一般写二分的思考顺序是这样的：首先通过题目背景和check(mid)函数的逻辑，判断答案落在左半区间还是右半区间。
左右半区间的划分方式一共有两种：

中点mid属于左半区间，则左半区间是[l, mid]，右半区间是[mid+1, r]，更新方式是r = mid;或者 l = mid + 1;，此时用第一个模板；
中点mid属于右半区间，则左半区间是[l, mid-1]，右半区间是[mid, r]，更新方式是r = mid - 1;或者 l = mid;，此时用第二个模板；

应用版本一：LeetCode 275. H-Index II;
应用版本二：LeetCode 69. Sqrt(x) ;
