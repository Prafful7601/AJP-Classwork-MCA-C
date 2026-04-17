package streammap;

import java.util.Arrays;
import java.util.List;

public class StreamMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = Arrays.asList("alice", "bob", "charlie", "diana");

		// map: convert each name to uppercase
		List<String> upper = names.stream()
				.map(n -> n.toUpperCase())
				.toList();
		System.out.println(upper);

		// map: get length of each name
		List<Integer> lengths = names.stream()
				.map(n -> n.length())
				.toList();
		System.out.println(lengths);

		// map: square each number
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> squares = numbers.stream()
				.map(n -> n * n)
				.toList();
		System.out.println(squares);
	}

}
