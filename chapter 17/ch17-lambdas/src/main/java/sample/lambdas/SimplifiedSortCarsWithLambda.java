package sample.lambdas;

import java.util.ArrayList;
import java.util.List;

public class SimplifiedSortCarsWithLambda {
	public static void main(String args[]) {
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car(10));
		cars.add(new Car(7));
		cars.add(new Car(5));

		cars.sort((o1, o2) -> compareCars(o1, o2));
	}

	private static int compareCars(Car o1, Car o2) {
		if (o1.getTopSpeed() == o2.getTopSpeed())
			return 0;
		else if (o1.getTopSpeed() > o2.getTopSpeed())
			return 1;
		else
			return -1;

	}
}
