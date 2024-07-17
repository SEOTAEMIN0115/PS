import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int l;
        int r;
        int c;
        int time;

        public Node(int l, int r, int c, int time){
            this.l = l;
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    static int l,r,c,result;
    static char[][][] building;
    static boolean[][][] visited;
    static int[] dc = {1,-1,0,0,0,0};
    static int[] dr = {0,0,-1,1,0,0};
    static int[] dl = {0,0,0,0,1,-1};
    static Node s,e;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            building = new char[l][r][c];
            visited = new boolean[l][r][c];

            if(l == 0 && r == 0 && c == 0) break;

            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < c; k++) {
                        building[i][j][k] = str.charAt(k);
                        if(building[i][j][k] == 'S'){
                            s = new Node(i,j,k,0);
                        } else if(building[i][j][k] == 'E') {
                            e = new Node(i,j,k,0);
                        }
                    }
                }
                br.readLine();
            }

            result = bfs();
            if(result == -1) System.out.println("Trapped!");
            else System.out.println("Escaped in " + result + " minute(s).");
        }
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        visited[s.l][s.r][s.c] = false;
        q.offer(new Node(s.l,s.r,s.c,0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.l == e.l && cur.r == e.r && cur.c == e.c) return cur.time;

            for (int i=0; i<6; i++){
                int nl = cur.l + dl[i];
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if(nl >= 0 && nl < l && nr >= 0 && nr < r && nc >= 0 && nc < c){
                    if(!visited[nl][nr][nc] && building[nl][nr][nc] != '#'){
                        visited[nl][nr][nc] = true;
                        q.offer(new Node(nl,nr,nc,cur.time+1));
                    }
                }
            }
        }
        return -1;
    }
}
