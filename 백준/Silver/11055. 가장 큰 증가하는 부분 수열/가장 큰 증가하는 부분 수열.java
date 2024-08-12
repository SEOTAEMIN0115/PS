import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dp = new int[n+1];
        int[] arr = new int[n+1];

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;

        dp[1] = arr[1];

        for (int i = 1; i <= n; i++) {
            dp[i] = arr[i];
           for (int j = 1; j <= i; j++) {
               if(dp[i]>dp[j]) {
                   dp[i] = Math.max(dp[i], arr[i] + dp[j]);
               }
           }
        }

        for(int i : dp){
            max = Math.max(max, i);
        }
        System.out.println(max);
    }
}