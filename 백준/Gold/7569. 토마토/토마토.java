import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Tomato {
    int x;
    int y;
    int z;

    Tomato(int z, int y, int x){
        this.z = z;
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int M; // 가로
    static int N; // 세로
    static int H; // 높이

    static int[] dx = { -1, 0, 1, 0, 0, 0 }; //상하좌우위아래
    static int[] dy = { 0, 1, 0, -1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, 1, -1 };

    static int[][][] tomato;
    static Queue<Tomato> queue;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][M][N];
        queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    //정수 1은 익은 토마토, 정수 0 은 익지 않은 토마토, 정수 -1은 토마토 X
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
//                    System.out.println("i = " + i + ", j = " + j + ", k = " + k);
                    if(tomato[i][j][k] == 1) {
                        queue.add(new Tomato(i, j, k));
                    }
                }
            }
        }
        System.out.println(bfs());
    }
    public static int bfs() {
        while(!queue.isEmpty()) {
            Tomato tomato = queue.remove();

            int z = tomato.z;
            int y = tomato.y;
            int x = tomato.x;

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i]; 
                int ny = y + dy[i]; 
                int nz = z + dz[i];

                if (nx >= 0 && ny >= 0 && nz >= 0 && nx < N && ny < M && nz < H) {
                    if (Main.tomato[nz][ny][nx] == 0) {
                      //익은 토마토 추가
                        queue.add(new Tomato(nz, ny, nx));

                        //익은 날짜를 얻기위해 그 전 값에서 1 증가
                        Main.tomato[nz][ny][nx] = Main.tomato[z][y][x] + 1;
                    }
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (tomato[i][j][k] == 0) return -1;
                    //날짜 최댓값 구하기
                    result = Math.max(result, tomato[i][j][k]);
                }
            }
        }

        if (result == 1) { //저장될 때부터 모든 토마토가 익어있는 상태
            return 0;
        } else {
            return result - 1;
        }
    }
}