import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;

        while (true) {
            if (bfsCnt(map) == 0) {
                answer = 0;
                break;
            }
            if (bfsCnt(map) >= 2) {
                break;
            }
            bfs();
            answer++;
        }
        System.out.println(answer);

    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    q.offer(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visit[nx][ny]) continue;
                if (map[nx][ny] == 0) {
                    if (map[x][y] > 0)
                        map[x][y]--;
                }

            }
        }
    }

    static int bfsCnt(int[][] maps) {
        int ans = 0;
        boolean[][] chk = new boolean[n][m];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i][j] != 0 && !chk[i][j]) {
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] tmp = queue.poll();
                        int tx = tmp[0];
                        int ty = tmp[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = tx + dx[d];
                            int ny = ty + dy[d];

                            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                            if (maps[nx][ny] == 0 || chk[nx][ny]) continue;
                            queue.offer(new int[]{nx, ny});
                            chk[nx][ny] = true;
                        }
                    }
                    ans++;
                }
            }
        }
        return ans;
    }
}