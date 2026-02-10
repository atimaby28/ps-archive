import java.io.*;
import java.util.*;

/**
 * 트라이 (Trie) - 문자열 삽입/검색/자동완성
 *
 * [입력]
 * 5 3           ← 삽입수 쿼리수
 * apple         ← 삽입할 단어들
 * app
 * application
 * banana
 * band
 * app           ← 검색/접두사 쿼리
 * ban
 * cat
 *
 * [출력]
 * app → 검색: true, 접두사 단어수: 3
 * ban → 검색: false, 접두사 단어수: 2
 * cat → 검색: false, 접두사 단어수: 0
 */
public class TrieTemplate {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            trie.insert(br.readLine().trim());
        }

        for (int i = 0; i < q; i++) {
            String query = br.readLine().trim();
            boolean found = trie.search(query);
            int count = trie.countWithPrefix(query);
            sb.append(query).append(" → 검색: ").append(found)
              .append(", 접두사 단어수: ").append(count).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEnd;
        int count; // 이 노드를 지나가는 단어 수
    }

    static class Trie {
        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                node.children.putIfAbsent(c, new TrieNode());
                node = node.children.get(c);
                node.count++;
            }
            node.isEnd = true;
        }

        boolean search(String word) {
            TrieNode node = find(word);
            return node != null && node.isEnd;
        }

        boolean startsWith(String prefix) {
            return find(prefix) != null;
        }

        int countWithPrefix(String prefix) {
            TrieNode node = find(prefix);
            return node == null ? 0 : node.count;
        }

        private TrieNode find(String str) {
            TrieNode node = root;
            for (char c : str.toCharArray()) {
                if (!node.children.containsKey(c)) return null;
                node = node.children.get(c);
            }
            return node;
        }
    }
}
