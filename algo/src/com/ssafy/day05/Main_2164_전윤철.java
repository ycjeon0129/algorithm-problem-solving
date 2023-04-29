package com.ssafy.day05;
// silver 4. 카드 2

import java.util.Scanner;

public class Main_2164_전윤철 {
	
	static int N, last;

	static QueueManager qm = new QueueManager();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		shake();
		
		System.out.println(last);
	}

	static void shake() {
		// 카드를 queue에 삽입
		for (int i=1; i<=N; i++) {
			qm.add(i);
		}
		Queue temp1 = null;
		Queue temp2 = null;
		// queue가 차있다면
		while (!qm.isEmpty()) {
			// 윗 장 제거 및 제거한 카드가 마지막 카드였다면 출력
			temp1 = qm.remove();
			if (qm.isEmpty()) {
				last = temp1.value;
				break;
			}
			// 윗 장을 가장 밑으로 제거 후 이동 및 제거한 카드가 마지막 카드였다면 출력
			temp2 = qm.remove();
			if (qm.isEmpty()) {
				last = temp2.value;
				break;
			}
			qm.add(temp2.value);
		}
	}

	static class Queue {
		int value;
		Queue nextNode;

		public Queue(int value) {
			this.value = value;
			this.nextNode = null;
		}

	}

	static class QueueManager {
		Queue front, rear;

		public QueueManager() {
			front = rear = null;
		}

		void add(int value) {
			Queue element = new Queue(value);
			if (isEmpty()) {
				rear = front = element;
			} else {
				rear.nextNode = element;
				rear = element;
			}
		}

		Queue peek() {
			if (isEmpty()) {
				return null;
			}
			return front;
		}

		Queue remove() {
			if (isEmpty()) {
				return null;
			}
			Queue pop = front;
			front = front.nextNode;
			return pop;
		}

		boolean isEmpty() {
			if (front == null) {
				return true;
			}
			return false;
		}
		
		int size() {
			Queue front2 = front;
			Queue rear2 = rear;
	        int count = 0;
	        while(front2 != rear2 && front2 != null) { //큐가 비어있는 경우가 있을수도 있을때도 생각해야함
	            count++;
	            front2 = front2.nextNode;
	        }
	        return count;
	    }

	}

}
