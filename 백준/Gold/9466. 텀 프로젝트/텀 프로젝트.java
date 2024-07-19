import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,t,count;
    static int[] arr;
    static boolean[] visited,done;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for (int test = 0; test < t; test++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr = new int[n+1];
            visited = new boolean[n+1];
            done = new boolean[n+1];
            count = 0;

            for(int i=1; i<=n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=n; i++){
                dfs(i);
            }
            System.out.println(n - count);
        }
    }

    static void dfs(int n) {
        if(visited[n]){
            done[n] = true;    // 팀 편성 완료했다고 처리
            count++;    // 팀 편성 완료 인원 증가
        }else{
            // 방문하지 않았을 때 -> 방문 처리!
            visited[n] = true;
        }

        // 다음 학생이 팀 결성을 아직 못했을 경우
        if(!done[arr[n]]){
            dfs(arr[n]);
        }

        visited[n] = false;
        done[n] = true;
    }
}