class Solution {
    public int numberOfSets(int n, int k) {
        long MOD = 1_000_000_007;
        int N = n + k - 1;
        int R = 2 * k;
        
        // If we need to choose more points than available, it's impossible
        if (R > N) {
            return 0;
        }
        
        // Compute nCr % MOD = (numerator * modular_inverse(denominator)) % MOD
        long numerator = 1;
        long denominator = 1;
        
        for (int i = 1; i <= R; i++) {
            numerator = (numerator * (N - i + 1)) % MOD;
            denominator = (denominator * i) % MOD;
        }
        
        long ans = (numerator * modInverse(denominator, MOD)) % MOD;
        return (int) ans;
    }
    
    // Function to calculate (a^(-1)) % m using Fermat's Little Theorem
    private long modInverse(long a, long m) {
        return power(a, m - 2, m);
    }
    
    // Helper function for modular exponentiation: (base^exp) % mod
    private long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }
}
