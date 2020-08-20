from operator import xor

def findSingleNumberIII(nums):
    xor = 0
    a = 0
    b = 0
    for num in nums:
        xor ^= num

    mask = xor & -xor

    # mask = 1
    # while(xor&mask == 0):
    #     mask = mask << 1

    for num in nums:
        if num&mask:
            a ^= num
        else:
            b ^= num
    return [a, b]


if __name__ == '__main__':
    samples = [[[3,2], [2,3], [1,2,3,1]],
            [[1,2], [2,1], [5,6,1,2,5,6]]]

    functions = [findSingleNumberIII]

    for pair in samples:
        for function in functions:
            assert pair[0] == function(pair[2]) or pair[1] == function(pair[2])
