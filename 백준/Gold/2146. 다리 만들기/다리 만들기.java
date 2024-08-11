import java.util.*;
import java.io.*;

public class Main { // (x) 참고 코드
    static int N;
    static int res = 10001;
    static int[][] req = new int[101][101];
    static boolean[][] isIslandCheck = new boolean[101][101];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static void dfs(int y, int x, int islandNum){
        isIslandCheck[y][x] = true;
        req[y][x] = islandNum;

        for(int i=0;i<4;i++){
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny<0 || ny>=N || nx<0 || nx>=N) continue;

            if(req[ny][nx] != 0 && !isIslandCheck[ny][nx]) dfs(ny,nx,islandNum);
        }
    }

    static void enlargeIsland(int[][] map, int islandNum){
        boolean[][] check = new boolean[101][101];
        Queue<Island> queue = new LinkedList<>();

        // 해당 섬을 큐에 추가
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == islandNum){
                    queue.add(new Island(i,j,0));
                    check[i][j] = true;
                }
            }
        }

        while(!queue.isEmpty()){
            Island position = queue.poll();

            for(int i=0;i<4;i++){
                int ny = position.y + dy[i];
                int nx = position.x + dx[i];
                if(ny<0 || ny>=N || nx<0 || nx>=N) continue;

                // 만약 이동한 값이 다른 섬이었을 경우 현재 cnt값을 res와 비교
                if(map[ny][nx] != islandNum && req[ny][nx] != 0){
                    if(res > position.cnt && position.cnt != 0) res = position.cnt;
                }
                else{
                    // 이동한 좌표가 바다이고 체크되지 않았을 경우
                    if(map[ny][nx]==0 && !check[ny][nx]){
                        check[ny][nx] = true;
                        queue.add(new Island(ny,nx, position.cnt+1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                req[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 같은 섬끼리 그룹핑
        int islandNum = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!isIslandCheck[i][j] && req[i][j] != 0){
                    dfs(i,j,islandNum);
                    islandNum++;
                }
            }
        }

        // 각 섬 별로 육지를 확장시켜 다른 육지와 닿았을 때의 값의 최솟값을 구함
        for(int i=1;i<=islandNum;i++){
            int[][] map = req.clone();
            enlargeIsland(map,i);
        }

        System.out.println(res);
    }
}

class Island{
    int y;
    int x;
    int cnt;
    Island(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}