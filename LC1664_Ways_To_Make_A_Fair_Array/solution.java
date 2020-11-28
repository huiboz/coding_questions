// smart solution by @lee215 from leetcode reference: https://leetcode.com/problems/ways-to-make-a-fair-array/discuss/944544/JavaPythonPython-Easy-and-Concise
public int waysToMakeFair(int[] A) {
    int res = 0, n = A.length, left[] = new int[2], right[] = new int[2];
    for (int i = 0; i < n; i++)
        right[i%2] += A[i]; 
    for (int i = 0; i < n; i++) {
        right[i%2] -= A[i];
        if (left[0]+right[1] == left[1]+right[0]) res++;
        left[i%2] += A[i];
    }
    return res;
}

// my own not smart solution
class Solution {
    public int waysToMakeFair(int[] nums) {
        int evenIndexSum = 0;
        int oddIndexSum = 0;
        int[] evenIndexCumSum = new int[nums.length];
        int[] oddIndexCumSum = new int[nums.length];
        
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                evenIndexSum += nums[i];
                evenIndexCumSum[i] += nums[i];
                if (i + 2 < nums.length)
                    evenIndexCumSum[i] += evenIndexCumSum[i + 2];
            } else {
                oddIndexSum += nums[i];
                oddIndexCumSum[i] += nums[i];
                if (i + 2 < nums.length)
                    oddIndexCumSum[i] += oddIndexCumSum[i + 2];
            }
        }
        
        int res = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                int temp = (i + 1) < nums.length ? oddIndexCumSum[i + 1] : 0;
                if (evenIndexSum - 2 * evenIndexCumSum[i] + 2 * temp == oddIndexSum - nums[i])
                    res++;
            } else {
                int temp = (i + 1) < nums.length ? evenIndexCumSum[i + 1] : 0;
                if (oddIndexSum - 2 * oddIndexCumSum[i] + 2 * temp == evenIndexSum - nums[i])
                    res++;                
            }
        }
        
        
        return res;
    }
}