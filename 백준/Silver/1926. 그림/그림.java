
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[501][501];
    static boolean[][] visit = new boolean[501][501];
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M, cnt, max;

    static class Spot {
        int x, y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(cnt + "\n" + max);

    }

    public static void bfs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if(board[i][j] == 0 || visit[i][j]) continue;
                cnt++;
                visit[i][j] = true;

                Queue<Spot> q = new LinkedList<Spot>();
                q.add(new Spot(i, j));

                int area = 0;

                while(!q.isEmpty()) {
                    area++;
                    Spot cur = q.poll();

                    for (int dir = 0; dir < 4; dir++) {
                        int nx = cur.x + dx[dir];
                        int ny = cur.y + dy[dir];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                        if(visit[nx][ny] || board[nx][ny] == 0) continue;
                        visit[nx][ny] = true;
                        q.add(new Spot(nx, ny));
                    }

                    max = Math.max(max, area);
                }

            }
        }
    }
}
