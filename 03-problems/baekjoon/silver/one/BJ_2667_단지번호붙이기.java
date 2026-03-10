package baekjoon.silver.one;

import java.io.*;
import java.util.*;

public class BJ_2667_단지번호붙이기 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> group;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        group = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        String result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {
        StringBuilder sb = new StringBuilder();

        int count = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;
                group.add(bfs(i, j, count));
            }
        }

        Collections.sort(group);

        sb.append(group.size()).append("\n");

        for (Integer k : group) {
            sb.append(k).append("\n");
        }

        return sb.toString();
    }

    private static int bfs(int row, int col, int count) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        int k = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
             int curCol = cur[1];

            for (int d = 0; d < 4; d++) {
                int newRow = curRow + dr[d];
                int newCol = curCol + dc[d];

                if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) continue;
                if (visited[newRow][newCol] || map[newRow][newCol] == 0) continue;

                k++;
                visited[newRow][newCol] = true;
                map[newRow][newCol] = count;
                queue.offer(new int[]{newRow, newCol});
            }
        }

        return k;
    }
}
