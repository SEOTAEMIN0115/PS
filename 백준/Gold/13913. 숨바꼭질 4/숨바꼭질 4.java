import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int location;
        int time;

        public Node(int location, int time) {
            this.location = location;
            this.time = time;
        }
    }
    static int n , k;
    static int[] map = new int[100001];
    static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n);
    }

    static void bfs(int value) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(value, 0));
        visited[value] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.location == k) {
                System.out.println(node.time);

                // 역추적은 후입선출인 Stack을 활용하면 편하다.
                Stack<Integer> stack = new Stack<>();
                int num = node.location;
                while (num != n) {
                    stack.add(num);
                    num = map[num];
                }
                // 위의 반복문엔 시작점인 N이 포함되지 않았기때문에 추가시켜준다.
                stack.add(num);

                while (!stack.isEmpty()) {
                    System.out.print(stack.pop() + " ");
                }

                break;
            }

            if (node.location + 1 < 100001 && !visited[node.location + 1]) {
                q.offer(new Node(node.location + 1, node.time + 1));
                visited[node.location + 1] = true;
                map[node.location + 1] = node.location;
            }

            if (node.location - 1 >= 0  && !visited[node.location - 1]) {
                q.offer(new Node(node.location - 1, node.time + 1));
                visited[node.location - 1] = true;
                map[node.location - 1] = node.location;
            }

            if (node.location * 2 < 100001 && !visited[node.location * 2]) {
                q.offer(new Node(node.location * 2, node.time + 1));
                visited[node.location * 2] = true;
                map[node.location * 2] = node.location;
            }

        }
    }

}