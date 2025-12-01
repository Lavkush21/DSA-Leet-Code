import java.util.Arrays;
import java.util.stream.LongStream;

class Solution {
    public long maxRunTime(int n, int[] batteries) {
    
        long sum = Arrays.stream(batteries).asLongStream().sum();
       
        Arrays.sort(batteries);
        
 
        int i = batteries.length - 1;


        while (i >= 0 && batteries[i] > sum / n) {
 
            sum -= batteries[i];
   
            n--;
       
            i--;
        }
        

        return sum / n;
    }
}
