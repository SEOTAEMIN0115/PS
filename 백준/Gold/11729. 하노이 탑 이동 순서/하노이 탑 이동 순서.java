import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        hanoitop(n,1,2,3);
        System.out.println(count);
        System.out.println(sb);
    }

    static void hanoitop(int n , int from , int tmp , int to) {
        if(n == 1) {
            count++;
            sb.append(from + " " + to + "\n");
            return;
        }
        else {
            hanoitop(n-1, from, to, tmp);
            sb.append(from + " " + to + "\n");
            count++;
            hanoitop(n-1, tmp, from, to);
        }
    }
}