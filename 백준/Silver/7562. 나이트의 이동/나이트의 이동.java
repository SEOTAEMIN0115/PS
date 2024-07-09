import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] chess;
    static boolean[][] visited;
    static int[] dx = {2,1,-1,-2,-2,-1,1,2};
    static int[] dy = {1,2,2,1,-1,-2,-2,-1};
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            q = new LinkedList<>();
            int L = Integer.parseInt(br.readLine());
            chess = new int[L][L];
            visited = new boolean[L][L];
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            q.offer(new int[]{x1,y1});
            visited[x1][y1] = true;

            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int x = cur[0];
                int y = cur[1];

                if(x == x2 && y == y2) {
                    break;
                }
                for(int dir = 0 ; dir < 8 ; dir++) {
                    int newX = x + dx[dir];
                    int newY = y + dy[dir];

                    if(newX < 0 || newY < 0 || newX >= L || newY >= L ) continue;
                    if(visited[newX][newY] || chess[newX][newY] != 0) continue;
                    chess[newX][newY] = chess[x][y] + 1;
                    q.offer(new int[]{newX, newY});
                }
            }
            System.out.println(chess[x2][y2]);
        }
    }
}
