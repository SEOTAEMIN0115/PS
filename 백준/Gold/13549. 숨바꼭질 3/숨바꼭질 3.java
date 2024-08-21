import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int x;
        int time;

        public Node(int x, int time){
            this.x = x;
            this.time = time;
        }
    }
    static int n , k;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited = new boolean[100001];
    static Queue<Node> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n);
        System.out.println(min);
    }

    static void bfs(int n) {
        q.offer(new Node(n, 0));
        visited[n] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == k) {
                min = Math.min(min, cur.time);
            }

            if(cur.x * 2 <= 100000 && !visited[cur.x * 2]) {
                q.offer(new Node(cur.x * 2, cur.time));
                visited[cur.x * 2] = true;
            }

            if(cur.x - 1 >= 0 && !visited[cur.x - 1]) {
                q.offer(new Node(cur.x - 1, cur.time + 1));
                visited[cur.x - 1] = true;
            }

            if(cur.x + 1 <= 100000 && !visited[cur.x + 1]) {
                q.offer(new Node(cur.x + 1, cur.time + 1));
                visited[cur.x + 1] = true;
            }
        }
    }
}