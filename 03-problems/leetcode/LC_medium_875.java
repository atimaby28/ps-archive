package leetcode;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LC_medium_875 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] piles = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) piles[i] = Integer.parseInt(st.nextToken());

        int h = Integer.parseInt(br.readLine());

        long result = solution(piles, h);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static long solution(int[] piles, int h) {
        long answer = 0;

        int n = piles.length;

        Arrays.sort(piles);

        long left = 1, right = piles[n - 1];
        while(left <= right) {
            // 시간 당 먹는 바나나의 갯수
            long mid = left + (right - left) / 2;

            long k = 0;
            for(int i = 0; i < n; i++) {
                k += (piles[i] + mid - 1) / mid;
            }

            if (k <= h) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}
