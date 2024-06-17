import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int last = 0;
    static int first = 0;
    static int[] queue = new int[10001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            switch (str) {
                case "push": push(Integer.parseInt(st.nextToken())); break;
                case "pop": sb.append(pop()).append("\n"); break;
                case "size": sb.append(size()).append("\n"); break;
                case "empty": sb.append(empty()).append("\n"); break;
                case "front": sb.append(front()).append("\n"); break;
                case "back": sb.append(back()).append("\n"); break;
            }
        }
        System.out.println(sb);
    }

    public static int back() {
        if(last-first == 0){
            return -1;
        } else {
            int b = queue[last - 1];
            return b;
        }
    }

    public static int front() {
        if(last-first == 0){
            return -1;
        } else {
            int f = queue[first];
            return f;
        }
    }

    public static int empty() {
        if(last-first == 0){
            return 1;
        } else {
            return 0;
        }
    }

    public static int size() {
        return last - first;
    }

    public static int pop() {
        if(last - first == 0){
            return -1;
        } else {
            int p = queue[first];
            first++;
            return p;
        }
    }

    public static void push(int num) {
        queue[last] = num;
        last++;
    }
}