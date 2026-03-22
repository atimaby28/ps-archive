package programmers.level2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PG_level2_의상 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        String[][] clothes = new String[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            clothes[i][0] = st.nextToken();
            clothes[i][1] = st.nextToken();
        }

        int result = solution(clothes);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> style = new HashMap<>();

        for (String[] clothe : clothes) {
            style.put(clothe[1], style.getOrDefault(clothe[1], 0) + 1);
        }

        for (String key : style.keySet()) {
            answer *= (style.get(key) + 1);
        }

        return answer - 1;
    }

}
