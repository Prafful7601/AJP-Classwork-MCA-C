package predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Predicate<Integer> isEven = n -> n % 2 == 0;
		Predicate<Integer> isPositive = n -> n > 0;

		System.out.println("Is 4 even? " + isEven.test(4));
		System.out.println("Is 7 even? " + isEven.test(7));

		// and: even AND positive
		Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);
		System.out.println("Is -4 even and positive? " + isEvenAndPositive.test(-4));
		System.out.println("Is 6 even and positive? " + isEvenAndPositive.test(6));

		// negate
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		List<Integer> odds = numbers.stream().filter(isEven.negate()).toList();
		System.out.println("Odd numbers: " + odds);
	}

}
