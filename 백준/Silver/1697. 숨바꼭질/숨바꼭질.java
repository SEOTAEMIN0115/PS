import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,k,next;
    static int[] move = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // n과 k가 같을 경우

        if(n == k){
            System.out.println(0);
        } else {
            bfs(n);
        }
    }

    static void bfs(int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        move[n] = 1; // 0인경우는 이미 선언

        while(!queue.isEmpty()){
            int location = queue.poll();
            // n이 x-1 , x+1 , 2*x만큼 이동하는 경우
            for(int i=0; i<3; i++){
                if(i == 0){
                    next = location + 1;
                } else if(i == 1){
                    next = location - 1;
                } else if(i == 2){
                    next = location * 2;
                }

                // 같은 경우의 리턴
                if(next == k){
                    System.out.println(move[location]);
                    return;
                }
                // 범위 이동
                if(next >= 0 && next < move.length && move[next] == 0){
                    queue.add(next);
                    move[next] = move[location] + 1;
                }
            }
        }
    }
}