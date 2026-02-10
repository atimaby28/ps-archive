import java.io.*;
import java.util.*;

/**
 * 정수론 (Number Theory) - 에라토스테네스의 체
 *
 * [입력] - N 이하 소수 출력
 * 30
 *
 * [출력]
 * 2 3 5 7 11 13 17 19 23 29
 *
 * [GCD/LCM - 입력]
 * 12 18
 *
 * [GCD/LCM - 출력]
 * GCD: 6
 * LCM: 36
 */
public class NumberTheoryTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int max = Integer.parseInt(br.readLine().trim());

        boolean[] isPrime = sieve(max);

        for (int i = 2; i <= max; i++) {
            if (isPrime[i]) sb.append(i).append(' ');
        }
        sb.append('\n');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    // ===== 에라토스테네스의 체 =====
    public static boolean[] sieve(int max) {
        boolean[] isPrime = new boolean[max + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= max; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= max; j += i) isPrime[j] = false;
            }
        }
        return isPrime;
    }

    // ===== 소수 판별 (단일) =====
    public static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // ===== GCD (유클리드 호제법) =====
    public static long gcd(long a, long b) {
        while (b != 0) { long t = b; b = a % b; a = t; }
        return a;
    }

    // ===== LCM =====
    public static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    // ===== 소인수분해 =====
    public static List<int[]> factorize(int n) {
        List<int[]> factors = new ArrayList<>();
        for (int i = 2; i * i <= n; i++) {
            int cnt = 0;
            while (n % i == 0) { n /= i; cnt++; }
            if (cnt > 0) factors.add(new int[]{i, cnt});
        }
        if (n > 1) factors.add(new int[]{n, 1});
        return factors;
    }
}
