package baekjoon.gold.two;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_17779_게리멘더링2 {

    static int N;
    static int[][] map;

    static final int LINE = 5;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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
        int answer = Integer.MAX_VALUE;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (r + d1 + d2 >= N) continue;
                        if (c - d1 < 0) continue;
                        if (c + d2 >= N) continue;
                        answer = Math.min(answer, getMin(r, c, d1, d2, new int[N][N]));
                    }
                }
            }
        }

        return answer;
    }

    private static int getMin(int r, int c, int d1, int d2, int[][] district) {
        int total = 0, dist1 = 0, dist2 = 0, dist3 = 0, dist4 = 0;

        // 경계선 1: top → left (↙ 대각선)
        for (int i = 0; i <= d1; i++) {
            district[r + i][c - i] = LINE;
        }

        // 경계선 2: top → right (↘ 대각선)
        for (int i = 0; i <= d2; i++) {
            district[r + i][c + i] = LINE;
        }

        // 경계선 3: left → bottom (↘ 대각선)
        for (int i = 0; i <= d2; i++) {
            district[r + d1+ i][c - d1 + i] = LINE;
        }

        // 경계선 4: right → bottom (↙ 대각선)
        for (int i = 0; i <= d1; i++) {
            district[r + d2 + i][c + d2 - i] = LINE;
        }

        // 1번 선거구
        for (int row = 0; row < r + d1; row++) {
            for (int col = 0; col <= c; col++) {
                if (district[row][col] == LINE) break;
                dist1 += map[row][col];
            }
        }

        // 2번 선거구
        for (int row = 0; row <= r + d2; row++) {
            for (int col = N - 1; col > c; col--) {
                if (district[row][col] == LINE) break;
                dist2 += map[row][col];
            }
        }
        
        // 3번 선거구
        for (int row = r + d1; row < N; row++) {
            for (int col = 0; col < c - d1 + d2; col++) {
                if (district[row][col] == LINE) break;
                dist3 += map[row][col];
            }
        }

        // 4번 선거구
        for (int row = r + d2 + 1; row < N; row++) {
            for (int col = N - 1; col >= c - d1 + d2; col--) {
                if (district[row][col] == LINE) break;
                dist4 += map[row][col];
            }
        }


        // 전체
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                total += map[i][j];
            }
        }

        int dist5 = total - (dist1 + dist2 + dist3 + dist4);

        int[] districts = {dist1, dist2, dist3, dist4, dist5};

        Arrays.sort(districts);

        return districts[4] - districts[0];
    }
}
