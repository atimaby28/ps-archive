package baekjoon.gold.five;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2212_센서 {

    static int N, K;
    static int[] broadcast;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        broadcast = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            broadcast[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int answer = 0;

        int[] distance = new int[N - 1];

        Arrays.sort(broadcast);

        for (int i = 0; i < N - 1; i++) {
            distance[i] = broadcast[i + 1] - broadcast[i];
        }

        Arrays.sort(distance);

        for (int i = 0; i < N - K; i++) {
            answer += distance[i];
        }

        return answer;
    }
}
