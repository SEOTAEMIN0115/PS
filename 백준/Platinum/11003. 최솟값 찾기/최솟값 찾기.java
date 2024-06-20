import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int value; // 값
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수열 A1 .. A2 .. An이 주어짐 -- L
        int n = Integer.parseInt(st.nextToken()); // 숫자 개수
        int l = Integer.parseInt(st.nextToken());

        //Di = Ai-l+1 - Ai --> i - (i-L+1) - i
        //새로운 수의 삽입이 필요
        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            // 최솟값을 비교하여 앞에값과 뒤의 값을 삭제.
            while (!deque.isEmpty() && deque.getLast().value > num) { // O(n)
                deque.removeLast();
            }
            deque.addLast(new Node(num, i));

            // 범위 벗어날경우 값의 삭제
            if(deque.getFirst().index <= i-l){
                deque.removeFirst();
            }
            bw.write(deque.getFirst().value + " ");
        }
        bw.flush();
        bw.close();
    }
}