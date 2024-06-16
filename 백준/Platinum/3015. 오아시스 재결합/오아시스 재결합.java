import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main { // P5 - 오아시스 재결합
    static long count;
    static int n;
    static Stack<Pair> stack;
    static class Pair { // 키 , 쌍의 높이안에 있는 사람들을 카운팅
        int height , count;

        Pair(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine()); // input

        stack = new Stack<Pair>();
        // 조건 1. A - B 가 서로 마주 보려면 A또는 B보다 키가 큰 사람이 없어야할것.
        // 조건 2. N줄에 사람의 키가 각각 주어졋을 경우 서로 볼수 있는 쌍의 수를 구하기
        // 조건 3. 2에 31승 - INT형의 값보단 작은 나노미터가 주어짐. 합쳣을때 값을 넘어가므로 long선언

        for(int i=0; i<n; i++) {
            // 스택 안에서 들어온값과 새로 들어온 값을 비교
            // 순서대로 비교 진행
            int currentHeight = Integer.parseInt(br.readLine());
            Pair pair = new Pair(currentHeight, 1);
            // top에 있는 높이가 현재 높이보다 작다면 pop
            while (!stack.isEmpty() && stack.peek().height <= currentHeight) {
                Pair p = stack.pop();
                count += p.count;
                if(p.height == currentHeight) {
                    pair.count += p.count;
                }
            }
            if(!stack.isEmpty()) {
                count++;
            }
            stack.push(pair);
        }
        System.out.println(count);
    }
}