package baekjoon.gold.five;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_12865_평범한배낭 {
    
    static int N, K;
    static int[][] items;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        items = new int[N][2];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            items[i][0] = Integer.parseInt(st.nextToken());
            items[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int result = solution();
        
        bw.write(result + "\n");
        
        bw.flush();
        
        bw.close();
        br.close();
    }

    private static int solution() {
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                if (w >= items[i - 1][0]) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i - 1][w - items[i - 1][0]] + items[i - 1][1]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[N][K];
    }

    // 공간 복잡도 최적화
    /*
    private static int solution() {
        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            for (int w = K; w >= items[i][0]; w--) {  // 역순!
                dp[w] = Math.max(dp[w], dp[w - items[i][0]] + items[i][1]);
            }
        }

        return dp[K];
    }
    */
}
