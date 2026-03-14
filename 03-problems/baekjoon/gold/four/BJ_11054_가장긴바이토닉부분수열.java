package baekjoon.gold.four;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11054_가장긴바이토닉부분수열 {

    static int N;
    static int[] bitonicArray;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        bitonicArray = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            bitonicArray[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int answer = 0;

        int[] increasing = new int[N];
        int[] decreasing = new int[N];

        Arrays.fill(increasing, 1);
        Arrays.fill(decreasing, 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (bitonicArray[j] < bitonicArray[i])
                    increasing[i] = Math.max(increasing[i], increasing[j] + 1);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (bitonicArray[j] < bitonicArray[i])
                    decreasing[i] = Math.max(decreasing[i], decreasing[j] + 1);
            }
        }

        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, increasing[i] + decreasing[i] - 1);
        }

        return answer;
    }
}
