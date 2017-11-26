package sample.methodref;

import java.util.ArrayList;
import java.util.List;

public class MethodRefs {
	public static void main(String args[]) {
		List<Car> cars = new ArrayList<Car>();
		cars.add(new Car(10));
		cars.add(new Car(7));
		cars.add(new Car(5));

		cars.sort((o1, o2) -> MyUtils.compareCars(o1, o2));
		//cars.sort(MyUtils::compareCars);

		cars.stream().mapToInt(car -> car.getTopSpeed()).forEach(e -> System.out.println(e));
		//cars.stream().mapToInt(car -> car.getTopSpeed()).forEach(System.out::println);
	}
}
