import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int x; int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int m,n,k;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] square;
    static boolean[][] visited;
    static int area;
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        square = new int[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int y=y1; y<y2; y++) {
                for(int x=x1; x<x2; x++) {
                    square[y][x] = 1;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j] && square[i][j] == 0) {
                    bfs(i,j);
                    area++;
                }
            }
        }

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }
        System.out.println(area);
        System.out.println(sb);
    }

    static void bfs(int i, int j) {
        int squareArea = 1;

        Queue<Node> queue = new LinkedList<>();
        visited[i][j] = true;

        queue.offer(new Node(i, j));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = node.x + dx[dir];
                int ny = node.y + dy[dir];

                if(isRange(nx,ny)){
                    if(!visited[nx][ny] && square[nx][ny] == 0){
                        queue.offer(new Node(nx,ny));
                        visited[nx][ny] = true;
                        squareArea++;
                    }
                }
            }
        }
        list.add(squareArea);
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}