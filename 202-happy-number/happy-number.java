class Solution {
    public int sumofSquaresOfDigits(int n){
        int sum = 0;
        while(n > 0){
            int dig=  n % 10;
            sum = sum + (dig * dig);
            n = n / 10;
        }
        return sum;
        
    }
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        while(fast !=1){
            slow = sumofSquaresOfDigits(slow);
            fast =  sumofSquaresOfDigits(sumofSquaresOfDigits(fast));
            if(fast == 1) {
                return true;
            }
            if(slow == fast) {
                return false;
            }
        }
        return true;
    }
}