func sortedSquares(A []int) []int {
    size := len(A)
    res := make([]int, size)
    l, r, k := 0, size - 1, size - 1
    for ; l <= r; {
        if A[l] + A[r] < 0 {
            res[k] = A[l] * A[l]
            l++
        } else {
            res[k] = A[r] * A[r]
            r--
        }
        k--
    }
    return res
}
