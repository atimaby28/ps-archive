package programmers.level3;

import java.io.*;
import java.util.*;

public class PG_level3_단어변환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String begin = st.nextToken();
        String target = st.nextToken();

        int n = Integer.parseInt(br.readLine());

        String[] words = new  String[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            words[i] = st.nextToken();
        }

        int result = solution(begin, target, words);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        answer = bfs(begin, target, words);

        return answer;
    }

    private static int bfs(String begin, String target, String[] words) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.offer(new Node(begin, 0));
        visited.add(begin);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (curNode.word.equals(target)) return curNode.count;

            for (String word : words) {
                if (visited.contains(word)) continue;
                if (!canConverted(curNode.word, word)) continue;

                visited.add(word);
                queue.offer(new Node(word, curNode.count + 1));
            }
        }

        return 0;
    }

    private static boolean canConverted(String curWord, String targetWord) {
        int diff = 0;
        for (int i = 0; i < curWord.length(); i++) {
            if (curWord.charAt(i) != targetWord.charAt(i)) diff++;
        }

        return diff == 1;
    }

    static class Node {
        String word;
        int count;
        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
