package programmers.level2;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class PG_level2_전화번호목록 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        String[] phone_book = new String[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            phone_book[i] = st.nextToken();
        }

        boolean result = solution(phone_book);

        bw.write(result ? "true" : "false" + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Arrays.sort(phone_book);


        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }

        return answer;
    }

}
