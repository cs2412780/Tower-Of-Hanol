package towerOfHanol;

import homework2.ArrayStack;

public class TowerOfHanol {
	
	public static void doHanolGame(int disk, 
			ArrayStack<Integer> source,
			ArrayStack<Integer> spare,
			ArrayStack<Integer> destination) {
		
		int step = 0;
		int limit = (int) (Math.pow(2, disk) - 1);
		ArrayStack<Integer> temp;
		if(disk == 1) {
			destination.push(source.pop());
		}
		else if(disk % 2 == 1) {
			while(step < limit) {
				step = doFourMoves(step, limit, source, spare, destination);
				temp = source;
				source = spare;
				spare = destination;
				destination = temp;
			}
		}
		else {
			while(step < limit) {
				step = doFourMoves(step, limit, source, destination, spare);
				temp = destination;
				destination = spare;
				spare = source;		
				source = temp;
			}
		}
	}

	private static int doFourMoves(int step, int limit,
			ArrayStack<Integer> source, 
			ArrayStack<Integer> spare, 
			ArrayStack<Integer> destination) {
		
		destination.push(source.pop());
		spare.push(source.pop());
		spare.push(destination.pop());
		step += 4;
		if(step < limit - 1) {
			if(destination.isEmpty()){
				destination.push(source.pop());
			}
			else if (source.isEmpty()) {
				source.push(destination.pop());
			}
		
			else {
				if(destination.peek() > source.peek()) {
					destination.push(source.pop());
				}
				else {
					source.push(destination.pop());
				}
			}
		}
		return step;
	}
	
	public static void recursive(int n, 
			ArrayStack<Integer> source, 
			ArrayStack<Integer> spare, 
			ArrayStack<Integer> destination) {
		if(n == 1) {
			destination.push(source.pop());
		}
		else {
			recursive(n - 1, source, destination, spare);
			destination.push(source.pop());
			recursive(n - 1, spare, source, destination);
		}
	}
	public static void main(String[] args) {

		int n = 11;
		ArrayStack<Integer> source = new ArrayStack<>();
		for(int i = n; i > 0; i--) {
			source.push(i);
		}
		
		ArrayStack<Integer> spare = new ArrayStack<>();
		ArrayStack<Integer> destination = new ArrayStack<>();
		System.out.println(source.isEmpty());
		System.out.println(destination.isEmpty());
		doHanolGame(n, source, spare, destination);

		while(!destination.isEmpty()) {
			System.out.println(destination.pop());
		}
		System.out.println(source.isEmpty());
		System.out.println(spare.isEmpty());

	}

}
