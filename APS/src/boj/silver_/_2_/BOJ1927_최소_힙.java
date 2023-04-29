package boj.silver_._2_;
// 절대 다시 풀어

import java.io.*;
import java.util.ArrayList;

public class BOJ1927_최소_힙 {

    static int N;
    static minHeap heap;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        heap = new minHeap();

        for (int i = 0; i < N; i++) {
            int op = Integer.parseInt(br.readLine());
            if (op == 0) {
                bw.write(String.format("%d\n", heap.delete()));
            } else  {
                heap.insert(op);
            }
        }
        bw.flush();
        bw.close();
    }

    static public class minHeap{
        private ArrayList<Integer> heap;

        //최소힙 생성자
        public minHeap() {
            heap = new ArrayList<Integer>();
            heap.add(0); // 0번째 인덱스는 사용 안합
        }

        //삽입
        public void insert(int val) {
            heap.add(val);
            int p = heap.size()-1;    //p는 새로 삽입한 노드의 인덱스 정보
            while(p>1 && heap.get(p/2)>heap.get(p)) {
                //새로 삽입한 노드의 위치가 1 초과이고 부모가 자식보다 크면 진행 ->새로 삽입한 노드의 위치가 루트까지 가거나 새로 삽입한 노드가 부모보다 클때까지 진행
                int tmp = heap.get(p/2);//부모 노드의 값
                heap.set(p/2, val);
                heap.set(p, tmp);

                p /= 2;    //새로 삽입한 노드가 한 레벨 상승했으니 인덱스 부모 노드 인덱스 값으로 변경
            }
        }
        //삭제
        public int delete() {
            //힙 이 비어있으면 0리턴
            if(heap.size()-1 < 1) {
                return 0;
            }

            //삭제할 노드, 루트 노드
            int deleteitem = heap.get(1);

            //마지막 노드를root에 삽입하고 마지막 노드 삭제
            heap.set(1,heap.get(heap.size()-1));
            heap.remove(heap.size()-1);

            int pos = 1; //루트에 새로 삽입한 노드의 인덱스 정보

            //pos*2는 왼쪽자식의 인덱스 값, 자식의 인덱스 값이 힙의 사이즈 값보다 크다는것은 더이상 삽입할 위치를 벗어났다는뜻
            while((pos*2)<heap.size()) {
                int min = heap.get(pos*2);//왼쪽 자식의 값
                int minPos = pos*2;// 왼쪽 자식의 인덱스 값

                //오른쪽 자식의 인덱스가 사이즈보다 작고 왼쪽 보다 더 작을때 오른쪽 자식을 부모와 바꿔줄 자식으로 지정
                if(((pos*2+1)<heap.size()) && min > heap.get(pos*2+1)) {
                    min = heap.get(pos*2 +1);
                    minPos = pos*2 +1;
                }

                //부모가 더 작으면 그만
                if(min > heap.get(pos))
                    break;

                //부모 자식 교환
                int tmp = heap.get(pos);
                heap.set(pos,heap.get(minPos));
                heap.set(minPos, tmp);
                pos = minPos;
            }

            return deleteitem;
        }

    }

//    static class MinimalHeap {
//        int idx;
//        int[] values;
//
//        public MinimalHeap(int value) {
//            values = new int [value+1];
//            idx = 0;
//        }
//
//        void add(int value) {
//            values[++idx] = value;
//            int tempIdx = idx;
//            while (tempIdx>1 && values[tempIdx]<values[tempIdx/2]) {
//                swap(tempIdx, tempIdx/2);
//                tempIdx /= 2;
//            }
//        }
//
//        int get() {
//            if (idx == 0) {
//                return 0;
//            }
//            int root = values[1];
//            if (idx > 1) {
//                values[1] = values[idx];
//            }
//            int tempIdx = 1;
//            while (tempIdx*2 <= idx) {
//                int min = tempIdx*2;
//                if (tempIdx*2+1<=idx && values[min]>values[tempIdx*2+1]) {
//                    min++;
//                }
//                if (values[min] > values[tempIdx]) break;
//                swap(tempIdx, min);
//            }
//
//
////            ////
////            values[idx--] = 0;
////            validChecker(1, true);
////            validChecker(1, false);
////            int tempIdx = 1;
////            while (tempIdx < idx) {
////                if (tempIdx*2<values.length && values[tempIdx]>values[tempIdx*2] && values[tempIdx*2]!=0) {
////                    swap(tempIdx, tempIdx*2);
////                    tempIdx *= 2;
////                } else if (tempIdx*2+1<values.length && values[tempIdx]>values[tempIdx*2+1] && values[tempIdx*2+1]!=0) {
////                    swap(tempIdx, tempIdx*2+1);
////                    tempIdx *= 2+1;
////                } else {
////                    break;
////                }
////            }
//
//            return root;
//        }
//
//        void validChecker(int start, boolean left) {
//            int adder = left ? 0 : 1;
//            int parent = start;
//            int child = start * 2 + adder;
//
//            if (child<=N && values[parent]>values[child] && values[child]!=0) {
//                swap(child, parent);
//                validChecker(child, true);
//                validChecker(child, false);
//            }
//        }
//
//        void swap(int idx1, int idx2) {
//            int temp = values[idx1];
//            values[idx1] = values[idx2];
//            values[idx2] = temp;
//        }
//    }
//
}
