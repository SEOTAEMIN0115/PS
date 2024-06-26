import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int M, N, count;
    public static int[][] maze;
    public static boolean[][] visited;
    static int[] xMove = {0, 0, -1, 1};
    static int[] yMove = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);

        visited = new boolean[N][M];
        maze = new int[N][M];
        count = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            char[] charArray = line.toCharArray();
            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(charArray[j]));
            }
        }

        bfs(0, 0);

        bw.write(maze[N - 1][M - 1] + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x, int y) {
        Queue<spot> queue = new LinkedList<>();
        queue.add(new spot(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            spot start = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newX = start.x + xMove[i];
                int newY = start.y + yMove[i];

                if (newX >= 0 && newX < N) {
                    if (newY >= 0 && newY < M) {
                        if (maze[newX][newY] != 0 && !visited[newX][newY]) {
                            queue.add(new spot(newX, newY));
                            maze[newX][newY] = maze[start.x][start.y] + 1;
                            visited[newX][newY] = true;
                        }
                    }
                }
            }

        }
    }

    static class spot {
        int x;
        int y;

        spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}