package baekjoon.gold.four;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2110_공유기설치 {

    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        long result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static long solution() {
        long answer = 0;

        Arrays.sort(houses);

        // 인접 공유기 사이의 최솟값의 범위
        long left = 1, right = houses[N - 1] - houses[0];

        while (left <= right) {
            // 인접 공유기 사이의 최솟값
            long mid = left + (right - left) / 2;

            int count = 1, lastPos = 0;
            for (int i = 1; i < N; i++) {
                if (houses[i] - houses[lastPos] >= mid) {
                    count++;
                    lastPos = i;
                }
            }

            if (count < C) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }

        return answer;
    }
}
