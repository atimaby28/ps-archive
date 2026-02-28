package programmers.ps.level2;

import java.io.*;
import java.util.StringTokenizer;

public class PG_level2_카펫 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int brown = Integer.parseInt(st.nextToken());
        int yellow = Integer.parseInt(st.nextToken());

        int[] result = solution(brown, yellow);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int[] solution(int brown, int yellow) {
        int[] answer = {};

        int total = brown + yellow;

        for (int i = 3; i * i <= total; i++) {
            if (total % i == 0 && ((i - 2) * ((total / i) - 2)) == yellow) {
                return new int[] { (total / i), i};
            }
        }

        return answer;
    }

}
