package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_6593_상범빌딩 {
    static int L, R, C;
    static char[][][] building;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            building = new char[L][R][C];

            int startL = 0, startR = 0, startC = 0;
            int endL = 0, endR = 0, endC = 0;

            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String line = br.readLine();
                    for (int c = 0; c < C; c++) {
                        building[l][r][c] = line.charAt(c);

                        if (building[l][r][c] == 'S') {
                            startL = l;
                            startR = r;
                            startC = c;
                        } else if (building[l][r][c] == 'E') {
                            endL = l;
                            endR = r;
                            endC = c;
                        }
                    }
                }
                br.readLine(); // 각 층 사이의 빈 줄 처리
            }

            sb.append(solution(startL, endL, startR, endR, startC, endC)).append("\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    private static String solution(int startL, int endL, int startR, int endR, int startC, int endC) {
        return "";
    }
}
