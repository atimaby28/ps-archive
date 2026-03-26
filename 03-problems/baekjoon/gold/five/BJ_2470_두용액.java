package baekjoon.gold.five;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_2470_두용액 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[] liquid = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        String result = solution(liquid);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution(int[] liquid) {
        Arrays.sort(liquid);

        int left = 0, right = N - 1;

        int[] answer = {liquid[left], liquid[right]};
        int target = Math.abs(liquid[left] + liquid[right]);

        while (left < right) {
            int sum = liquid[left] + liquid[right];

            if (Math.abs(sum) < target) {
                target = Math.abs(sum);

                answer[0] = liquid[left];
                answer[1] = liquid[right];
            }

            if (sum < 0) left++;
            else if (sum > 0) right--;
            else break;  // 최적
        }

        return answer[0] + " " + answer[1];
    }


}
