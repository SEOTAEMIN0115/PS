import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] dp = new int[41];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        int answer = 1;
        int seat = 0;

        for (int i = 1; i <= m; i++) {
            int tmp = Integer.parseInt(br.readLine());
            answer *= dp[tmp - seat - 1];
            seat = tmp;
        }
        
        answer *= dp[n - seat];
        System.out.println(answer);
    }
}