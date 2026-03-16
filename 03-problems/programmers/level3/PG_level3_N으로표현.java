package programmers.level3;

import java.io.*;
import java.util.*;

public class PG_level3_N으로표현 {

    static int N;
    static int number;

    static final int LIMIT = 8;

    static List<Set<Integer>> dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        number = Integer.parseInt(br.readLine());

        int result = solution(N, number);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    private static int solution(int N, int number) {
        dp = new ArrayList<>();

        dp.add(new HashSet<>()); // zero index, dummy

        for (int i = 1; i <= LIMIT; i++) {
            dp.add(new HashSet<>());
            dp.get(i).add(Integer.parseInt(String.valueOf(N).repeat(i)));

            for (int j = 1; j < i; j++) {
                for (int a : dp.get(j)) {
                    for (int b : dp.get(i - j)) {
                        dp.get(i).add(a + b);
                        dp.get(i).add(a - b);
                        dp.get(i).add(a * b);
                        if (b != 0) dp.get(i).add(a / b);
                    }
                }
            }

            if (dp.get(i).contains(number)) return i;
        }

        return -1;
    }
}
