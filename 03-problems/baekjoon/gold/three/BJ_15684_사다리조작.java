package baekjoon.gold.three;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_15684_사다리조작 {

    static int N, M, H;
    static boolean[][] map;

    static final int LIMIT = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int horizontal = Integer.parseInt(st.nextToken());
            int vertical = Integer.parseInt(st.nextToken());

            map[horizontal][vertical] = true;
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {

        // 가로선 0~3개
        for (int target = 0; target <= LIMIT; target++) {
            if (dfs(0, 0, target)) return target;
        }
        return -1;

    }

    // 2차원을 1차원으로
    private static boolean dfs(int depth, int start, int target) {
        if (depth == target) return simulate();
        for (int pos = start; pos < H * (N - 1); pos++) {
            int h = pos / (N - 1) + 1;
            int v = pos % (N - 1) + 1;
            if (!map[h][v - 1] &&  !map[h][v] && !map[h][v + 1]) {
                map[h][v] = true;
                if (dfs(depth + 1,pos + 1, target)) return true;
                map[h][v] = false;
            }
        }
        return false;
    }

//    private static boolean dfs(int depth, int target, int startH, int startV) {
//        if (depth == target) { return simulate(); }
//        for (int h = startH; h <= H; h++) {
//            for (int v = (h == startH ? startV : 1); v < N; v++) {
//                if (!map[h][v] && !map[h][v - 1] && !map[h][v + 1]) {
//                    map[h][v] = true;
//                    if (dfs(depth + 1, target, h, v + 1)) return true;
//                    map[h][v] = false;
//                }
//            }
//        }
//        return false;
//    }
    private static boolean simulate() {
        for (int v = 1; v <= N; v++) {
            int cv = v;
            for (int h = 1; h <= H; h++) {
                if (map[h][cv]) cv += 1;
                 else if (map[h][cv - 1]) cv -= 1;
            }
            if (cv != v) return false;
        }

        return true;
    }

}
