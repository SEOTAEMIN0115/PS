import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        int start = 0;
        while(n-- > 0){
            int num = Integer.parseInt(br.readLine());

            if(num > start){
                for(int i=start+1; i<=num; i++){ // start+1 부터 num값까지 값 넣음
                    stack.push(i); // 값을 추가
                    sb.append("+").append("\n");
                }
                start = num; // 다음 넣은 값의 오름차순을 유지하구 위해 변수 초기화
            } else if (stack.peek() != num){
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append("-").append("\n");
        }
        System.out.println(sb);
    }
}