from functools import reduce
from operator import xor

def findMissingFromSortedArray(arr):
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

def findMissingFromRandomArrayUsingSum(arr):
    return sum(range(len(arr)+2)) - sum(arr)

def findMissingFromRandomArrayUsingXOR(arr):
    res1 = arr[0]
    res2 = 1

    for i in range(1, len(arr)):
        res1 = res1 ^ arr[i]

    for i in range(2, len(arr)+2):
        res2 = res2 ^ i

    return res1 ^ res2

def findMissingFromRandomArrayUsingXORReduce(arr):
    return reduce(xor, arr) ^ reduce(xor, range(1, len(arr)+2))

if __name__ == '__main__':
    samples = [[5, [1,2,3,4,6,7,8]],
            [3, [1,2,4,5,6,7,8]],
            [1, [2]],
            [5, [1,2,3,4]],
            [3, [1,2]],
            [2, [1]]]

    functions = [findMissingFromSortedArray, findMissingFromRandomArrayUsingSum,
        findMissingFromRandomArrayUsingXOR, findMissingFromRandomArrayUsingXORReduce]

    for pair in samples:
        for function in functions:
            assert pair[0] == function(pair[1])
