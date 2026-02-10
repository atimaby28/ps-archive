import java.io.*;
import java.util.*;

/**
 * 빠른 입출력 템플릿
 * BufferedReader + BufferedWriter 조합
 */
public class FastReader {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        // 풀이 작성

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
