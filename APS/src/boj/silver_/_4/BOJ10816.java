//package boj.silver;
//
//import java.io.*;
//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class BOJ10816 {
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    static StringTokenizer st;
//
//    static int[] result;
//
//    public static void main(String[] args) throws IOException {
//
//        BOJ10816 bst = new BOJ10816();
//
//        int N = Integer.parseInt(br.readLine());
//        st = new StringTokenizer(br.readLine());
//        for (int i=0; i<N; i++) {
//            int temp = Integer.parseInt(st.nextToken());
//            Node cur = search(bst.root, temp);
//            if (cur==null) {
//                bst.root = bst.insert(bst.root, temp);
//            } else {
//                cur.count++;
//            }
//        }
//        int M = Integer.parseInt(br.readLine());
//        result = new int[M];
//        st = new StringTokenizer(br.readLine());
//        for (int i=0; i<M; i++) {
//            int target = Integer.parseInt(st.nextToken());
//            Node temp = search(bst.root, target);
//            if (temp == null) {
//                result[i] = 0;
//            } else {
//                result[i] = temp.count;
//            }
//            bst.delete(bst.root, target);
//        }
//
//        for (int i=0; i<M; i++) {
//            bw.write(result[i] + " ");
//        }
//        bw.flush();
//        bw.close();
//
//    }
//
//    Node root;
//
//    class Node {
//        int value;
//        int count=1;
//        Node left;
//        Node right;
//        Node parent;
//
//        Node(int value) {
//            this(value, null);
//        }
//        Node(int value, Node parent) {
//            this.value = value;
//            this.parent = parent;
//            this.left = null;
//            this.right = null;
//        }
//    }
//    static Node search(Node root, int value) {
//        if (root==null || root.value==value) {
//            return root;
//        }
//        if (root.value < value) {
//            return search(root.right, value);
//        }
//        return search(root.left, value);
//    }
//    Node insert(Node root, int value) {
//        if (root == null) {
//            root = new Node(value);
//            return root;
//        } else if (value < root.value) {
//            root.left = insert(root.left, value);
//        } else if (value > root.value) {
//            root.right = insert(root.right, value);
//        } else {
//            root.count++;
//        }
//        return root;
//    }
//
//    Node delete(Node root, int value) {
//        if (root==null) {
//            return root;
//        }
//        if (value < root.value) {
//            root.left = delete(root.left, value);
//        } else if (value > root.value) {
//            root.right = delete(root.right, value);
//        } else {
//            if (root.left == null) {
//                return root.right;
//            } else if (root.right == null) {
//                return root.left;
//            }
//            Node temp = minNode(root.right);
//            root.value = temp.value;
//            root.right = delete(root.right, temp.value);
//        }
//        return root;
//    }
//
//    Node minNode(Node node) {
//        Node cur = node;
//        while (cur.left != null) {
//            cur = cur.left;
//        }
//        return cur;
//    }
//
//}
package boj.silver_._4;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10816 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int[] result;
    static int[] card = new int[20_000_002];

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            card[temp+10_000_000]++;
        }
        int M = Integer.parseInt(br.readLine());
        result = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++) {
            int target = Integer.parseInt(st.nextToken());
            result[i] = card[target+10_000_000];
        }

        for (int i=0; i<M; i++) {
            bw.write(result[i] + " ");
        }
        bw.flush();
        bw.close();

    }
}
