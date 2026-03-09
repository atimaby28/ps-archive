package baekjoon.silver.two;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1654_랜선자르기 {

    static int K, N;
    static int[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        K =  Integer.parseInt(st.nextToken());
        N =  Integer.parseInt(st.nextToken());

        line = new int[K];
        for (int i = 0; i < K; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        long result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static long solution() {
        long answer = 0;

        Arrays.sort(line);

        long left = 1, right = line[K - 1];

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long count = 0;
            for (int i = 0; i < K; i++) {
                count += line[i] / mid;
            }

            if (count < N) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }
}
