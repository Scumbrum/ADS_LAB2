package src;

import src.BinaryTree.BinaryTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
public class Main {



	public static void main(String[] args) {
		BinaryTree<Integer> first = new BinaryTree<>();
		Random random = new Random();
		for (int i = 0; i < 1000000; i++) {
			first.add(random.nextInt(1000000));
		}
		ArrayList<Long> measures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			long current = System.currentTimeMillis();
			first.add(random.nextInt());
			long result = System.currentTimeMillis() - current;
			measures.add(result);
		}
		stat(measures);
		measures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			long current = System.currentTimeMillis();
			first.find(i);
			long result = System.currentTimeMillis() - current;
			measures.add(result);
		}
		stat(measures);
		measures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			long current = System.currentTimeMillis();
			first.sum();
			long result = System.currentTimeMillis() - current;

			measures.add(result);
		}
		stat(measures);
		measures = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			long current = System.currentTimeMillis();
			first.remove(i);
			long result = System.currentTimeMillis() - current;
			measures.add(result);
		}
		stat(measures);

	}
		public static void stat(ArrayList<Long> measures){
			long max = measures.get(0);
			long min = measures.get(0);
			double average = 0;
			long sum = 0;
			for (long i : measures) {
				sum += i;
				if (i < min) {
					min = i;
					continue;
				}
				if (i > max) {
					max = i;
				}
			}
			average = sum / measures.size();
			System.out.println(max);
			System.out.println(min);
			System.out.println(average);
		}
	}
