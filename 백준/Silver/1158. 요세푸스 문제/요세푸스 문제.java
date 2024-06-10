import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken()); // n명
        int k = Integer.parseInt(st.nextToken()); // k번째 수

        LinkedList<Integer> list = new LinkedList<>();

        for(int i=1; i<=n; i++) {
            list.add(i); // list에 1~7까지 추가
        }

        sb.append('<'); // 출력 첫번째 < 추가
        while(!list.isEmpty()) {
            for(int i=0; i<k; i++) {
                if(i == k-1) { // i번은 1base 이기때문에 k번째는 -1로 순번을 맞춘다
                    if(list.size() == 1){
                        sb.append(list.remove());
                    } else {
                        sb.append(list.remove() + ", ");
                    }
                } else {
                    list.add(list.remove());
                }
            }
        }
        sb.append('>');
        System.out.println(sb);
    }
}
