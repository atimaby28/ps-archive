package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_6593_상범빌딩 {
    static int L, R, C;
    static char[][][] building;
    static boolean[][][] visited;

    // 상하(Level), 앞뒤(Row), 좌우(Col)
    static int[] dl = {-1, 1,  0, 0,  0, 0};  // 아래층, 위층
    static int[] dr = { 0, 0, -1, 1,  0, 0};  // 앞, 뒤
    static int[] dc = { 0, 0,  0, 0, -1, 1};  // 왼, 오

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
            visited = new boolean[L][R][C];

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

            sb.append(solution(startL, startR, startC, endL, endR, endC)).append("\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    private static String solution(int startL, int startR, int startC, int endL, int endR, int endC) {
        // #: 지나갈 수 없는 칸, '.': 비어 있는 칸
        // 도착 지점이 있기 때문에 큐 배엘어 넣어도 레벨 단위 가능
        return bfs(startL, startR, startC, endL, endR, endC);
    }

    private static String bfs(int startL, int startR, int startC, int endL, int endR, int endC) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{startL, startR, startC, 0});
        visited[startL][startR][startC] = true;

        int minutes = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int curL = cur[0];
            int curR = cur[1];
            int curC = cur[2];
            int curMinutes = cur[3];

            if (curL == endL && curR == endR && curC == endC) return "Escaped in " + curMinutes + " minute(s).";

            for (int d = 0; d < 6; d++) {
                int newL = curL + dl[d];
                int newR = curR + dr[d];
                int newC = curC + dc[d];

                if (newL < 0 || newL >= L || newR < 0 || newR >= R || newC < 0 || newC >= C) continue;
                if (visited[newL][newR][newC] || building[newL][newR][newC] == '#') continue;

                visited[newL][newR][newC] = true;
                queue.offer(new int[]{newL, newR, newC, curMinutes + 1});
            }
        }

        return "Trapped!";
    }
}
