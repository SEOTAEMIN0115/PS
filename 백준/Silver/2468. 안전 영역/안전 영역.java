import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,maxHeight,count,max;
    static int[][] rain;
    static boolean[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        rain = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                rain[i][j] = Integer.parseInt(st.nextToken());
                if (rain[i][j] > maxHeight) {
                    maxHeight = rain[i][j];
                }
            }
        }

        for (int height = 0; height < maxHeight + 1; height++) {
            visited = new boolean[n][n];
            count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && rain[i][j] > height) {
                        count += dfs(i,j,height);
                    }
                }
            }
            max = Math.max(max, count);
        }
        System.out.println(max);
    }

    static int dfs(int x, int y, int height) {
        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
             int nx = x + dx[k];
             int ny = y + dy[k];

             if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1) continue;
             if(visited[nx][ny]) continue;
             if(rain[nx][ny] > height) {
                 dfs(nx,ny,height);
             }
        }
        return 1;
    }
}