package programmers.level2;

import java.io.*;

public class PG_level2_모음사전 {

    static int count, answer;
    static char[] alphabet = {'A', 'E', 'I', 'O', 'U'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = br.readLine();

        int result = solution(word);

        bw.write(result + "\n");

        bw.flush();

        bw.close();
        br.close();
    }

    public static int solution(String target) {

        dfs("", target);

        return answer;
    }

    private static void dfs(String current, String target) {
        // 1. 현재 단어가 목표와 일치하는지 확인
        if (current.equals(target)) {
            answer = count;
            return;
        }

        // 2. 길이가 5라면 더 이상 뒤에 붙일 수 없음
        if (current.length() == alphabet.length) {
            return;
        }

        for (int i = 0; i < alphabet.length; i++) {
            count++;
            dfs(current + alphabet[i], target);
        }
    }
}
