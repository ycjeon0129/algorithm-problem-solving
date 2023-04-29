package boj.silver_._2_;
// 트리, bfs

import java.io.*;
import java.util.*;

public class BOJ11725_트리의_부모_찾기 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] parents;
    static Node[] tree;
    static Queue<Node> queue;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        tree = new Node[N+1];
        tree[1] = new Node(1);

        int a, b;
        // 트리의 각 간선 정보를 반영
        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            add(a, b);
        }
        // 루트 노드부터 각 노드의 부모 저장
        setParent();
        for (int i = 2; i <= N; i++) {
            bw.write(parents[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    static void add(int a, int b) {
        // 간선의 각 노드가 처음 추가된 것이라면 생성
        if (tree[a]==null) {
            tree[a] = new Node(a);
        }
        if (tree[b]==null) {
            tree[b] = new Node(b);
        }
        // 각 노드의 인접 리스트에 상대 노드 추가
        tree[a].family.add(b);
        tree[b].family.add(a);
    }

    // 값, 방문 여부, 인접 노드 리스트를 갖는 클래스
    static class Node {
        int value;
        boolean isVisited;
        List<Integer> family = new ArrayList<>();
        Node(int value) {
            this.value = value;
        }
    }

    // 루트 노드부터 큐에 담는 bfs 탐색
    static void setParent() {
        queue = new ArrayDeque<>();
        queue.offer(tree[1]);
        tree[1].isVisited = true;
        Node cur;
        while (queue.size() > 0) {
            cur = queue.poll();
            for (int i=0; i<cur.family.size(); i++) {
                // 이미 방문하지 않은 노드에 한하여 현 노드의 인접 노드의 부모를 현 노드로 설정
                if (!tree[cur.family.get(i)].isVisited) {
                    tree[cur.family.get(i)].isVisited = true;
                    parents[tree[cur.family.get(i)].value] = cur.value;
                    queue.offer(tree[cur.family.get(i)]);
                }
            }
        }
    }
}

//public class BOJ11725_트리의_부모_찾기 {
//
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    static int N;
//    static Node[] tree;
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        tree = new Node[100_001];
//        tree[1] = new Node(1);
//        tree[1].isConnected = true;
//
//        int a, b;
//        for (int i = 2; i <= N; i++) {
//            st = new StringTokenizer(br.readLine());
//            a = Integer.parseInt(st.nextToken());
//            b = Integer.parseInt(st.nextToken());
//            add(a, b);
//        }
//        setParent();
//        for (int i = 2; i <= 100_000; i++) {
//            if (tree[i]==null) {
//                continue;
//            }
//            bw.write(tree[i].parent+"\n");
//        }
//        bw.flush();
//        bw.close();
//    }
//
//    static void add(int a, int b) {
//        if (tree[a]==null) {
//            tree[a] = new Node(a);
//        }
//        if (tree[b]==null) {
//            tree[b] = new Node(b);
//        }
//        tree[a].family.add(b);
//        tree[b].family.add(a);
//        if (tree[a].isConnected) {
//            tree[b].parent = a;
//            tree[b].isConnected = true;
//        } else if (tree[b].isConnected) {
//            tree[a].parent = b;
//            tree[a].isConnected = true;
//        }
//    }
//
//    static class Node {
//        int value, parent;
//        boolean isConnected;
//        List<Integer> family = new ArrayList<>();
//        Node(int value) {
//            this.value = value;
//        }
//    }
//
//    static void setParent() {
//        for (int i = 2; i <= 100_000; i++) {
//            if (tree[i] == null) {
//                continue;
//            }
//            if (tree[i].parent != 0) {
//                continue;
//            }
//            for (int j = 0; j < tree[i].family.size(); j++) {
//                if (tree[tree[i].family.get(j)].isConnected) {
//                    tree[i].parent = tree[i].family.get(j);
//                    tree[i].isConnected = true;
//                    break;
//                }
//            }
//        }
//    }
//}