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

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (i + d1 + d2 >= N) continue; // 아래 경계 한계
                        if (j - d1 < 0) continue;       // 왼쪽 경계 한계
                        if (j + d2 >= N) continue;      // 오른쪽 경계 한계

                        answer = Math.min(answer, getMin(i, j, d1, d2 , new int[N][N]));
                    }
                }
            }
        }

        return answer;
    }

    private static int getMin(int x, int y, int d1, int d2, int[][] district) {
        int total = 0, dist1 = 0, dist2 = 0, dist3 = 0, dist4 = 0;

        // 경계선 1: top → left (↙ 대각선)
        for (int i = 0; i <= d1; i++) {
            district[x + i][y - i] = 5;
        }

        // 경계선 2: top → right (↘ 대각선)
        for (int i = 0; i <= d2; i++) {
            district[x + i][y + i] = 5;
        }

        // 경계선 3: left → bottom (↘ 대각선)
        for (int i = 0; i <= d2; i++) {
            district[x + d1 + i][y - d1 + i] = 5;
        }

        // 경계선 4: right → bottom (↙ 대각선)
        for (int i = 0; i <= d1; i++) {
            district[x + d2 + i][y + d2 - i] = 5;
        }

        // 1번 선거구
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (district[i][j] == 5) break;
                dist1 += map[i][j];
            }
        }

        // 2번 선거구
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (district[i][j] == 5) break;
                dist2 += map[i][j];
            }
        }
        
        // 3번 선거구
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (district[i][j] == 5) break;
                dist3 += map[i][j];
            }
        }

        // 4번 선거구
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (district[i][j] == 5) break;
                dist4 += map[i][j];
            }
        }

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
