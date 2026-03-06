package baekjoon.gold.five;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_3079_입국심사 {

    static int N, M;
    static int[] line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 심사대, M: 친구 수
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        line = new int[N];

        for (int i = 0; i < N; i++) {
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

        long left = line[0];
        long right = (long) line[N - 1] * M;

        while (left <= right) {
            // 총 걸리는 시간
            long mid = left + (right - left) / 2;

            long sum = 0;
            for (int i = 0; i < N; i++) {
                sum += mid / line[i];
                if (sum >= M) break;
            }

            if (sum < M) {
                left = mid + 1;
            } else if (sum >= M) {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }
}
