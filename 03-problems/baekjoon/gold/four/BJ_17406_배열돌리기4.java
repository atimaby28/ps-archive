package baekjoon.gold.four;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17406_배열돌리기4 {

    static int N, M, K, A = Integer.MAX_VALUE;
    static int[][] array;
    static List<int[]> commands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        array = new int[N][M];
        commands = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            commands.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int result = solution();

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution() {
        permutation(0, new ArrayList<>(), new boolean[K]);

        return A;
    }

    private static void permutation(int depth, List<int[]> result, boolean[] visited) {
        if (depth == K) {
            int[][] copiedArray = new int[N][M];
            for (int i = 0; i < N; i++) {
                copiedArray[i] = array[i].clone();
            }

            A = Math.min(A, rotating(copiedArray, result));

            return;
        }

        for (int i = 0; i < K; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            result.add(commands.get(i));
            permutation(depth + 1, result, visited);
            result.remove(result.size() - 1);
            visited[i] = false;
        }
    }

    private static int rotating(int[][] copiedArray, List<int[]> result) {
        // (r - s, c - s), (r + s, c + s);

        int[] minRow = new int[N];

        for (int[] command : result) {
            int startRow = command[0] - command[2] - 1;
            int startCol = command[1] - command[2] - 1;
            int endRow = command[0] + command[2] - 1;
            int endCol = command[1] + command[2] - 1;

            int layers =  Math.min(endRow - startRow, endCol - startCol) / 2;

            for (int l = 0; l < layers; l++) {
                int top = startRow + l, left = startCol + l;
                int bottom = endRow - l, right = endCol - l;

                int temp = copiedArray[top][right];

                // →
                for (int j = right; j > left; j--) copiedArray[top][j] = copiedArray[top][j - 1];
                // ↑
                for (int j = top; j < bottom; j++) copiedArray[j][left] = copiedArray[j + 1][left];
                // ←
                for (int j = left; j < right; j++) copiedArray[bottom][j] = copiedArray[bottom][j + 1];
                // ↓
                for (int j = bottom; j > top; j--) copiedArray[j][right] = copiedArray[j - 1][right];

                copiedArray[top + 1][right] = temp;
            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                minRow[i] += copiedArray[i][j];
            }
        }

        Arrays.sort(minRow);

        return minRow[0];
    }
}
