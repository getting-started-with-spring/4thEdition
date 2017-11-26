package sample.lambdas;

public class Car {
	private int topSpeed;

	public Car(int topSpeed) {
		super();
		this.topSpeed = topSpeed;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	@Override
	public String toString() {
		return "Car [topSpeed=" + topSpeed + "]";
	}
}
