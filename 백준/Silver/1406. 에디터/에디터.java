import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class Main { // Linkedlist 와 Listiterator 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LinkedList<String> list = new LinkedList<String>();
        String[] str = br.readLine().split("");

        for (String s : str) {
            list.add(s);
        }

        int n = Integer.parseInt(br.readLine());

        ListIterator<String> iter = list.listIterator(); // 커서를 양방향 이동시키기 위해 선언

        while (iter.hasNext()) { // 처음 커서는 문장에 맨뒤에 존재해야하므로 이동시켜줌
            iter.next();
        }

        while (n-- > 0) {
            String[] str1 = br.readLine().split(" ");
            String first = str1[0];

            if(first.equals("P")){
                iter.add(str1[1]);
            } else if (first.equals("L")){
                if(iter.hasPrevious()) iter.previous(); // 커서가 이전 위치로 복귀
            } else if (first.equals("D")){
                if(iter.hasNext()) iter.next(); // 커서가 다음위치로 이동
            } else if (first.equals("B")){
                if(iter.hasPrevious()){
                    iter.previous();
                    iter.remove();
                }
            }
        }
        System.out.println(String.join("", list));
    }
}