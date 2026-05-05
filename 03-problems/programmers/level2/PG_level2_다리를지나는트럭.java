package programmers.level2;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PG_level2_다리를지나는트럭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        int bridge_length = Integer.parseInt(br.readLine());
        int weight = Integer.parseInt(br.readLine());

        int k = Integer.parseInt(br.readLine());
        int[] truck_weights = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            truck_weights[i] = Integer.parseInt(st.nextToken());
        }

        int result = solution(bridge_length, weight, truck_weights);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {

        // weight, time
        Queue<int[]> bridge = new ArrayDeque<>();

        int time = 0, idx = 0, onBridge = 0;

        while (!bridge.isEmpty() || idx < truck_weights.length) {
            time++;

            // 맨 앞 트럭이 다리 끝에 도달했으면 내보냄
            if (!bridge.isEmpty() && bridge.peek()[1] + bridge_length == time) {
                onBridge -= bridge.poll()[0];
            }

            // 새 트럭 진입 시도 무게, 대 수
            if (idx < truck_weights.length && onBridge + truck_weights[idx] <= weight && bridge.size() <= bridge_length) {
                bridge.offer(new int[]{truck_weights[idx], time});
                onBridge += truck_weights[idx];
                idx++;
            }

        }
        return time;
    }
}
