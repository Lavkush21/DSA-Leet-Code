class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        int turns = 0;

        while (n >= stonesToRemove) {
            n -= stonesToRemove;
            stonesToRemove--;
            turns++;
        }

        // Alice plays on odd turns (1st, 3rd, 5th...). 
        // If total completed turns is odd, Bob couldn't move next, so Alice wins.
        return turns % 2 == 1;
    }
}
