import java.io.*;
import java.util.*;

/**
 * 재귀 (Recursion) - 분할정복 거듭제곱
 *
 * [입력]
 * 2 10 1000     ← base exp mod
 *
 * [출력] - 2^10 % 1000
 * 24
 *
 * [하노이 탑 - 입력]
 * 3              ← 원판 수
 *
 * [하노이 탑 - 출력] - from to
 * 1 3
 * 1 2
 * 3 2
 * 1 3
 * 2 1
 * 2 3
 * 1 3
 */
public class RecursionTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long base = Long.parseLong(st.nextToken());
        long exp = Long.parseLong(st.nextToken());
        long mod = Long.parseLong(st.nextToken());

        sb.append(power(base, exp, mod)).append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 분할정복 거듭제곱 O(log n) =====
    public static long power(long base, long exp, long mod) {
        if (exp == 0) return 1;
        long half = power(base, exp / 2, mod);
        long result = half * half % mod;
        if (exp % 2 == 1) result = result * base % mod;
        return result;
    }

    // ===== 하노이 탑 =====
    public static void hanoi(int n, int from, int to, int via) {
        if (n == 1) {
            sb.append(from).append(' ').append(to).append('\n');
            return;
        }
        hanoi(n - 1, from, via, to);
        sb.append(from).append(' ').append(to).append('\n');
        hanoi(n - 1, via, to, from);
    }
}
