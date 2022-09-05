public class msbs2 {
    public static void main(String[] args) {

    }

    public int solution(int[] A, int X, int Y) {
        int n = A.length;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for( int i = 0; i < Y; i++ ){
            dp[i] = A[i];
            cnt[i] = 1;
        }
        for( int i = Y; i < n; i++ ){
            if( dp[i-1] < (dp[i-Y]+A[i]) ){
                if( cnt[i-Y] < X ){
                    dp[i] = dp[i-Y]+A[i];
                    cnt[i] = cnt[i-Y] + 1;
                } else {
                    if( A[i] < dp[i-1] ){
                        dp[i] = A[i];
                        cnt[i] = 1;
                    } else {
                        dp[i] = dp[i-1];
                        cnt[i] = cnt[i-1];
                    }
                }
            } else {
                dp[i] = dp[i-1];
                cnt[i] = cnt[i-1];
            }
        }
        int res = Integer.MAX_VALUE;
        for( int i = 0; i< n; i++ ){
            if( cnt[i] ==X ){
                res = Math.min(res, dp[i]);
            }
        }
        return res;
    }
}
