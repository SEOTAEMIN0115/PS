import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int x;
        int y;
        int count;
        int Destroyed;

        public Node(int x, int y, int count, int Destroyed){
            this.x = x;
            this.y = y;
            this.count = count;
            this.Destroyed = Destroyed;
        }
    }
    static int n,m,answer;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static int[][] map;
    static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m][2]; // 0은 벽을 한번도 안부숨 1은 부숨
        answer = -1;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(answer);
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            int curX = cur.x;
            int curY = cur.y;

            if(curX == n-1 && curY == m-1){ // 도착점이 도달할 경우
                answer = cur.count;
                return;
            }

            for(int i=0; i<4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= m ) continue;

                if(map[nx][ny] == 0 && !visited[nx][ny][cur.Destroyed]){ // 지나갈수 있는 길
                   visited[nx][ny][cur.Destroyed] = true;
                   queue.add(new Node(nx, ny, cur.count+1, cur.Destroyed));
                } else {
                    if(cur.Destroyed == 0 && !visited[nx][ny][cur.Destroyed+1]){
                        visited[nx][ny][cur.Destroyed+1] = true;
                        queue.add(new Node(nx, ny, cur.count+1, cur.Destroyed+1));
                    }
                }
            }
        }
    }
}