package baekjoon.gold.three;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15685_드래곤커브 {

    static int N;
    static int[][] curves;

    static final int LIMIT = 101;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        curves = new int[N][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            curves[i][0] = Integer.parseInt(st.nextToken());
            curves[i][1] = Integer.parseInt(st.nextToken());
            curves[i][2] = Integer.parseInt(st.nextToken());
            curves[i][3] = Integer.parseInt(st.nextToken());
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        int answer = 0;

        boolean[][] points = new boolean[LIMIT][LIMIT];

        // 커브 그리기
        for (int[] curve : curves) {
            int x = curve[0];
            int y = curve[1];
            int d = curve[2];
            int g = curve[3];

            draw(x, y, d, g, points);
        }

        // 네모칸 세기
        for (int i = 0; i < LIMIT - 1; i++) {
            for (int j = 0; j < LIMIT - 1; j++) {
                if (points[i][j] && points[i+1][j] && points[i][j+1] && points[i+1][j+1])
                    answer++;
            }
        }

        return answer;
    }

    private static void draw(int x, int y, int d, int g, boolean[][] map) {
        // 점 목록 저장할 리스트
        List<int[]> points = new ArrayList<>();

        // 0세대: 시작점 + 방향으로 한 칸 이동한 점
        points.add(new int[]{x, y});

        int nx = x + dx[d];
        int ny = y + dy[d];

        points.add(new int[]{nx, ny});

        // 1세대 ~ g세대
        for (int gen = 0; gen < g; gen++) {
            // 끝점
            int[] end = points.get(points.size() - 1);
            int endX = end[0], endY = end[1];

            // 끝점 제외한 기존 점들을 역순으로 회전
            int size = points.size();

            for (int i = size - 2; i >= 0; i--) {
                int px = points.get(i)[0];
                int py = points.get(i)[1];

                int newX = endX - (py - endY);
                int newY = endY + (px - endX);

                points.add(new int[]{newX, newY});

            }

        }

        // 모든 점을 map에 찍기
        for (int[] p : points) {
            if (p[0] >= 0 && p[0] <= 100 && p[1] >= 0 && p[1] <= 100)
                map[p[0]][p[1]] = true;
        }

    }
}
