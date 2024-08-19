import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,v;
    static boolean[] visited;
    static int[][] arr;
    static Queue<Integer> queue = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        arr = new int[n+1][n+1];
        visited = new boolean[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            arr[num1][num2] = arr[num2][num1] = 1;
        }
        dfs(v);
        sb.append('\n');
        visited = new boolean[n+1];
        bfs(v);
        System.out.println(sb);
    }

    static void bfs(int v) {
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            v = queue.poll();
            sb.append(v + " ");

            for (int i = 1; i <= n; i++) {
                if (arr[v][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    static void dfs(int v) {
        visited[v] = true;
        sb.append(v).append(" ");

        for (int i = 0; i <= n; i++) {
            if (arr[i][v] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

}