import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int t,w;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        t = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        int[] arr = new int[t+1];
        for (int i = 1; i <= t; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][][] cache = new int[3][t + 1][w + 2];

        for (int i = 1; i <= t; i++) {
            for (int j = 1; j <= w + 1; j++) {
                if (arr[i] == 1) {
                    cache[1][i][j] = Math.max(cache[1][i - 1][j], cache[2][i - 1][j - 1]) + 1;
                    cache[2][i][j] = Math.max(cache[2][i - 1][j], cache[1][i - 1][j - 1]);
                } else {
                    if (i == 1 && j == 1) continue;
                    cache[1][i][j] = Math.max(cache[1][i - 1][j], cache[2][i - 1][j - 1]);
                    cache[2][i][j] = Math.max(cache[2][i - 1][j], cache[1][i - 1][j - 1]) + 1;
                }
            }
        }

        int result = 0;
        for (int i = 1; i <= w + 1; i++) {
            result = Math.max(result, Math.max(cache[1][t][i], cache[2][t][i]));
        }

        System.out.println(result);
    }
}