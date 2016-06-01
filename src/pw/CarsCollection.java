package pw;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pw.objects.Car;

public class CarsCollection {
	private List<Car> cars;
	private static CarsCollection instance;

	private CarsCollection()
	{
		cars = new ArrayList<Car>();
	}

	public static CarsCollection instance()
	{
		if (instance == null) {
			instance = new CarsCollection();
		}

		return instance;
	}

	public Car newCar()
	{
		Integer R = new Integer((new Random()).nextInt(226) + 30);
		Integer G = new Integer((new Random()).nextInt(226) + 30);
		Integer B = new Integer((new Random()).nextInt(226) + 30);

		Car car = new Car(new Color(R, G, B));

		car.start();

		cars.add(car);

		return car;
	}

	public void notifyCars()
	{
		for (int i = 0, j = cars.size(); i < j; i++) {
			synchronized(cars.get(i)) {
				cars.get(i)
					.notify();
			}
		}
	}

	public void paint(Graphics g)
	{
		for (int i = 0, j = cars.size(); i < j; i++) {
			cars.get(i)
				.paint(g);
		}
	}
}
