//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.StringTokenizer;
//
//public class BOJ7785_회사에_있는_사람 {
//
//    static int N;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = null;
//
//        N = Integer.parseInt(br.readLine());
//        HashMap<String, Boolean> map = new HashMap<>(N);
//
//        String name, inout;
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            name = st.nextToken();
//            inout = st.nextToken();
//            if ("enter".equals(inout)) {
//                map.put(name, true);
//            } else {
//                map.remove(name);
//            }
//        }
//        StringBuilder sb = new StringBuilder();
//        map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(entry-> {
//            sb.append(entry.getKey() + "\n");
//        });
//        System.out.println(sb);
//    }
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ7785_회사에_있는_사람 {

    static int N, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        String name, inout;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            inout = st.nextToken();
            if ("enter".equals(inout)) {
                num++;
            } else {
                num--;
            }
        }
        System.out.println(num);
    }

    public class Node {
        String name;
        Node fore;
        Node rear;

        public Node(String name, Node fore, Node rear) {
            this.name = name;
            this.fore = fore;
            this.rear = rear;
        }
    }

    public class Linker {
        Node root;
        Node last;
        int num;

        void add(String name) {
            last.fore = new Node(name, last, null);
        }
    }
}
