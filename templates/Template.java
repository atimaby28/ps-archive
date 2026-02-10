import java.io.*;
import java.util.*;

/**
 * 문제: [문제 이름]
 * 링크: [문제 URL]
 * 난이도: [난이도]
 * 분류: [알고리즘 분류]
 * 풀이 시간: [소요 시간]
 *
 * [풀이 접근]
 * 1.
 * 2.
 * 3.
 */
public class Template {
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
