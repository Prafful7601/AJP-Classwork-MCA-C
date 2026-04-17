package optionalex;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// basic Optional usage
		Optional<String> name = Optional.of("Prafful");
		System.out.println("Present: " + name.isPresent());
		System.out.println("Value: " + name.get());

		// orElse: fallback if empty
		Optional<String> empty = Optional.empty();
		System.out.println(empty.orElse("Default Name"));

		// Optional with stream: find first even number
		List<Integer> numbers = Arrays.asList(1, 3, 5, 6, 7, 8);
		Optional<Integer> firstEven = numbers.stream()
				.filter(n -> n % 2 == 0)
				.findFirst();
		firstEven.ifPresent(n -> System.out.println("First even: " + n));

		// Optional with stream: no match
		Optional<Integer> result = numbers.stream()
				.filter(n -> n > 100)
				.findFirst();
		System.out.println(result.orElse(-1));
	}

}
