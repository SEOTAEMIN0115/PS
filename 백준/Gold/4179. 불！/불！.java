import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node{
        int x; int y; int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int r,c,answer;
    static char[][] mirror;
    static Queue<Node> fire = new LinkedList<>();
    static Queue<Node> person = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        mirror = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                mirror[i][j] = line.charAt(j);
                if(mirror[i][j] == 'J'){
                    person.add(new Node(i, j, 0));
                } else if(mirror[i][j] == 'F'){
                    fire.add(new Node(i, j, 0));
                }
            }
        }
        answer = 0;
        if(bfs()){
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }
    static boolean isRange(int x, int y) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
    static boolean bfs() {
        while (!person.isEmpty()) {
            int f_size = fire.size();
            for(int i=0;i<f_size;i++) {
                Node node = fire.poll();

                for(int d=0;d<4;d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];
                    if(0 <= nx && nx < r && 0 <= ny && ny < c) {
                        if(mirror[nx][ny] != '#' && mirror[nx][ny] !='F') {
                            mirror[nx][ny] = 'F';
                            fire.add(new Node(nx, ny, node.time+1));
                        }
                    }
                }

            }

            int person_size = person.size();

            for(int i=0;i<person_size;i++) {
                Node node = person.poll();

                for(int d=0;d<4;d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];
                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) {
                        answer = node.time+1;
                        return false;
                    }


                    if(mirror[nx][ny] != '#' && mirror[nx][ny] !='F' && mirror[nx][ny] != 'J') {
                        person.add(new Node(nx, ny, node.time+1));
                        mirror[nx][ny] = 'J';
                    }

                }

            }

        }
        return true;
    }
}
