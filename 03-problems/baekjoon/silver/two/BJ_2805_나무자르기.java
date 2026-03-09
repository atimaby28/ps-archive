package baekjoon.silver.two;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2805_나무자르기 {

    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        st =  new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        long result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static long solution() {
        long answer = 0;

        Arrays.sort(trees);

        long left = 1, right = trees[N - 1];

        while (left <= right) {
            long mid = left + (right - left) / 2;

            long height = 0;
            for (int i = 0; i < N; i++) {
                height += Math.max(0, trees[i] - mid);
            }

            if (height < M) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }

        }
        return answer;
    }
}
