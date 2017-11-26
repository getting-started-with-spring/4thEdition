package sample.methodref;


public class MyUtils {
	public static int compareCars(Car o1, Car o2) {
		if (o1.getTopSpeed() == o2.getTopSpeed())
			return 0;
		else if (o1.getTopSpeed() > o2.getTopSpeed())
			return 1;
		else
			return -1;

	}
}
