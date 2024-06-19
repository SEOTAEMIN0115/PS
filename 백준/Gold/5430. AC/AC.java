import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine()); // 테스트케이스

        for(int i = 0; i < t; i++) {
            String str = br.readLine(); // 명령

            int n = Integer.parseInt(br.readLine()); // 숫자의 개수
            Deque<Integer> deque = new LinkedList<>();
            int error = 0;
            boolean check = false;
            // 2,4,6,8로 []안에 번호 진행됨.
            StringTokenizer st = new StringTokenizer(br.readLine(),"[,]");
            for(int j=1; j<(n*2); j+=2){
                deque.offer(Integer.parseInt(st.nextToken())); // [] 배열안에 입력된 숫자를 deque에 넣는다.
            }

           for(int j=0; j<str.length(); j++){
               if(str.charAt(j) == 'R') { // 배열을 뒤집을 경우
                   check = !check;
               } else {
                   if(deque.isEmpty()) { // 덱이 빌경우 에러출력
                       error = 1;
                       break;
                   }
                   // 덱에 숫자가 있을경우 배열이 뒤집힌지 체크하고 앞이나 뒤의 숫자를 빼냄
                   if(!check) deque.pollFirst();
                   else deque.pollLast();
               }
           }

           if(error == 1) sb.append("error").append("\n"); // error 일경우 출력
           else { // error 아닐 경우 정상 출력
               if(check) {
                   sb.append("[");
                   while(!deque.isEmpty()) {
                       sb.append(deque.pollLast());
                       if(deque.isEmpty()) break;
                       sb.append(",");
                   }
                   sb.append("]").append("\n");
               } else {
                   sb.append("[");
                   while(!deque.isEmpty()) {
                       sb.append(deque.pollFirst());
                       if(deque.isEmpty()) break;
                       sb.append(",");
                   }
                   sb.append("]").append("\n");
               }
           }
        }
        System.out.println(sb);
    }
}
