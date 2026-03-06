package programmers.level2;

import java.io.*;
import java.util.StringTokenizer;

public class PG_level2_타겟넘버 {

    static int targetCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[] numbers = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int target = Integer.parseInt(br.readLine());

        int result = solution(numbers, target);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);

        return targetCount;
    }

    private static void dfs(int[] numbers, int target, int index, int value) {
        if (index == numbers.length) {
            if (value == target) targetCount++;
            return;
        }

        dfs(numbers, target, index + 1, value + numbers[index]);
        dfs(numbers, target, index + 1, value - numbers[index]);
    }

}
