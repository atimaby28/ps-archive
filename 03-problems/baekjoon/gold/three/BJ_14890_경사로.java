package baekjoon.gold.three;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14890_경사로 {

    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int route = 0;

        // 가로: N개 행 각각 검사
        for (int r = 0; r < N; r++) {
            if (check(map[r])) route++;  // 1차원 배열 하나를 검사
        }

        // 세로: N개 열 각각 검사
        for (int c = 0; c < N; c++) {
            // 열을 1차원 배열로 뽑아서 같은 함수로 검사
            int[] newCol = new int[N];
            for (int i = 0; i < N; i++) {
                newCol[i] = map[i][c];
            }
            if (check(newCol)) route++;
        }

        return route;
    }

    private static boolean check(int[] route) {
        // slope[i]: i번 칸에 경사로가 이미 놓였는지
        boolean[] isSlope = new boolean[N];

        for (int i = 0; i < N - 1; i++) {

            // 인접한 두 칸에 따라 조건이 분기
            int diff = route[i] - route[i + 1];

            // 차이 == 0 아무것도 안 함
            if (diff == 0) continue;

            if (diff == 1) { // route[i] - route[i+1] == 1 (내리막)

                if (i + L >= N) return false; // 경계 밖

                for (int j = i + 1; j <  i + 1 + L; j++) {
                    if (isSlope[j]) return false; // 그 L칸에 이미 경사로가 있으면 실패

                    if (route[i + 1] != route[j]) return false; // i + 1부터 L칸이 같은 높이여야 함

                    isSlope[j] = true; // 통과하면 slope 표시

                }
            } else if (diff == -1) { // route[i] - route[i+1] == -1 (오르막)

                if (i - L + 1 < 0) return false; // 경계 밖

                for (int j = i - L + 1; j <= i; j++) {
                    if (isSlope[j]) return false; // 그 L칸에 이미 경사로가 있으면 실패

                    if (route[i] != route[j]) return false; // i부터 왼쪽으로 L칸이 같은 높이여야 함

                    isSlope[j] = true; // 통과하면 slope 표시
                }
            } else return false; // |차이| >= 2, return false

        }

        return true;
    }
}
