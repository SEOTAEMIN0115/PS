import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int f,s,g,u,d;
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        visited = new int[f+1];

        bfs(f,s,g,u,d);
    }

    static void bfs(int f, int s, int g, int u, int d) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == g) {
                System.out.println(visited[cur]-1);
            } // 현재위치 - 도착점

            if (cur + u <= f && visited[cur+u] == 0) { // 현재위치 + 위로이동
                visited[cur + u] = visited[cur] + 1;
                q.add(cur + u);
            }

            if (cur - d > 0 && visited[cur-d] == 0) { // 현재위치 - 내려가는층
                visited[cur-d] = visited[cur] + 1;
                q.add(cur-d);
            }
        }
        if(visited[g] == 0)
            System.out.println("use the stairs");
    }
}