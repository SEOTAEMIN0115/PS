import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());

            int[][] dp = new int[2][n+1];
            int[][] sticker = new int[2][n+1];

            for (int j = 0; j < 2; j++) {
                String[] str = br.readLine().split(" ");

                for (int k = 1; k <= n; k++) {
                    sticker[j][k] = Integer.parseInt(str[k-1]);
                }
            }

            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            for (int j = 2; j <= n; j++) {
                dp[0][j] = Math.max(dp[1][j-1] , dp[1][j-2]) + sticker[0][j];
                dp[1][j] = Math.max(dp[0][j-1] , dp[0][j-2]) + sticker[1][j];
            }
            System.out.println(Math.max(dp[0][n] , dp[1][n]));
        }
    }
}