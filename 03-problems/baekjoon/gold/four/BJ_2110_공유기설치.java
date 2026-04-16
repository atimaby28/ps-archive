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
        int answer = 0;

        Arrays.sort(houses);

        // mid = 두 공유기 사이의 거리
        int lo = 1, hi = houses[N - 1] - houses[0];

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (canInstall(mid)) {
                answer = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return answer;

    }

    private static boolean canInstall(int mid) {
        int count = 1;           // 첫 집에 무조건 하나 설치
        int last = houses[0];    // 마지막 설치 위치

        for (int i = 0; i < N - 1; i++) {
            if (houses[i + 1] - last >= mid) {
                count++;
                last = houses[i + 1];
            }
        }
        return count >= C;
    }
}
