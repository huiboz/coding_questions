def findMissingsFromSortedArray(arr):
    low = 0
    high = len(arr) - 1

    while (low < high):
        mid = (low + high) // 2

        if (arr[mid] == mid + 1):
            low += 1
        else:
            high = mid

    if (low == len(arr) - 1 and arr[low] == low + 1):
        low += 1

    return low + 1

assert 5 == findMissingsFromSortedArray([1,2,3,4,6,7,8])
assert 3 == findMissingsFromSortedArray([1,2,4,5,6,7,8])
assert 1 == findMissingsFromSortedArray([2])
assert 5 == findMissingsFromSortedArray([1,2,3,4])
assert 3 == findMissingsFromSortedArray([1,2])
assert 2 == findMissingsFromSortedArray([1])
