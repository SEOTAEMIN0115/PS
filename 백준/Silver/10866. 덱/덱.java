import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] deq = new int[10001];
    static int first = 0;
    static int last = 0;
    static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String S = st.nextToken();

            switch (S) {
                case "push_front" :
                    push_front(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back" :
                    push_back(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front" :
                    sb.append(pop_front()).append("\n");
                    break;
                case "pop_back" :
                    sb.append(pop_back()).append("\n");
                    break;
                case "size" :
                    sb.append(size()).append("\n");
                    break;
                case "empty" :
                    sb.append(empty()).append("\n");
                    break;
                case "front" :
                    sb.append(front()).append("\n");
                    break;
                case "back" :
                    sb.append(back()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    public static void push_front(int X) {
        deq[first] = X;
        //first는 0부터 10000 > 9999로 점점 내려 갈 것이다.
        //필자는 0부터 값을 넣었지만 10000부터 넣어도 된다.
        //first는 0부터 시작이기에 0 -1이면 음수이기에 배열의 크기인 10001을 더하고, 나눠주고 나머지를 저장해준다.
        //10001을 나눠주는 이유는 이러게 하므로 10000에서 0으로 다시 돌아가는 경우를 따로 조건을 주지 않아도 된다.
        //다른 메소드에도 다 비슷한게 연산 될 것인데, 이유는 여기에 적힌대로 생각하면 된다.
        first = ((first - 1) + 10001) % 10001;
        size++;
    }

    public static void push_back(int X) {
        last = (last + 1) % 10001;
        deq[last] = X;
        size++;
    }

    public static int pop_front() {
        if(size == 0) {
            return -1;
        }else {
            first = (first + 1) % 10001;
            int PF = deq[first];
            size--;
            return PF;
        }
    }

    public static int pop_back() {
        if(size == 0) {
            return -1;
        }else {
            int PB = deq[last];
            last = ((last - 1) + 10001) % 10001;
            size--;
            return PB;
        }
    }

    public static int size() {
        return size;
    }

    public static int empty() {
        if(size == 0) {
            return 1;
        }else {
            return 0;
        }
    }

    public static int front() {
        if(size == 0) {
            return -1;
        }else {
            int F = deq[(first + 1) % 10001];
            return F;
        }
    }

    public static int back() {
        if(size == 0) {
            return -1;
        }else {
            int B = deq[last];
            return B;
        }
    }
}
