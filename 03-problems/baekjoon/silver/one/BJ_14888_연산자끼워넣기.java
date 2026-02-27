package baekjoon.silver.one;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_14888_연산자끼워넣기 {

    static int N;
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static final int operNum = 4;
    static int[] array, operation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        array = new int[N];
        operation = new int[operNum];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < operNum; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        String result = solution();

        bw.write(result);

        bw.flush();

        bw.close();
        br.close();
    }

    private static String solution() {

        dfs(0, array[0]);

        return min + "\n" + max;
    }

    private static void dfs(int depth, int result) {
        if (depth == N - 1) {
            min = Math.min(min, result);
            max = Math.max(max, result);
            return;
        }

        for (int i = 0; i < operNum; i++) {
            if (operation[i] <= 0) continue;

            operation[i]--;
            dfs(depth + 1, getResult(i, result, array[depth + 1]));
            operation[i]++;
        }
    }

    private static int getResult(int opType, int op1, int op2) {
        switch (opType) {
            case 0 -> {
                return op1 + op2;
            }
            case 1 -> {
                return op1 - op2;
            }
            case 2 -> {
                return op1 * op2;
            }
            case 3 -> {
                return op1 / op2;
            }
            default -> {
                return -1;
            }
        }
    }
}
