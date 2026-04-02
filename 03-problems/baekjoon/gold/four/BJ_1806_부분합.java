package baekjoon.gold.four;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_1806_부분합 {

    static int N, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution(arr);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int[] arr) {
        int left = 0, right = left + 1;

        int sum = 0, len = N;
        while (left < arr.length) {

            sum = arr[left] + arr[right];

            if (sum < S) {
                right++;
                sum += arr[right];
            } else if (sum >= S) {
                sum -= arr[left];
                left++;
            } else {
                len = Math.min(len, right - left + 1);
            }
        }

        return len;
    }
}
