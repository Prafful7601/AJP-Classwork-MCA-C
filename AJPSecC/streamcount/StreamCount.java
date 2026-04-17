package streamcount;

import java.util.Arrays;
import java.util.List;

public class StreamCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = Arrays.asList(10, 20, 10, 30, 20, 40, 50, 30);

		// count: total elements greater than 20
		long count = list.stream().filter(n -> n > 20).count();
		System.out.println("Count > 20: " + count);

		// distinct: remove duplicates
		List<Integer> unique = list.stream().distinct().toList();
		System.out.println("Distinct: " + unique);

		// limit: first 3 elements
		List<Integer> limited = list.stream().limit(3).toList();
		System.out.println("Limit 3: " + limited);

		// skip: skip first 3 elements
		List<Integer> skipped = list.stream().skip(3).toList();
		System.out.println("Skip 3: " + skipped);
	}

}
