package pw;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pw.objects.Car;

/**
 * @author ventaquil
 * @package pw
 * @version 0.1.0-alpha
 */
public class CarsCollection {
	private List<Car> cars;
	private static CarsCollection instance;

	/**
	 * Private class constructor. Creates new List of Car objects.
	 */
	private CarsCollection()
	{
		cars = new ArrayList<Car>();
	}

	/**
	 * Method returns existing instance of CarsCollection. If instance does not exists method creates it.
	 * 
	 * @return CarsCollection instance.
	 */
	public static CarsCollection instance()
	{
		if (instance == null) {
			instance = new CarsCollection();
		}

		return instance;
	}

	/**
	 * Method creates new Car object.
	 * 
	 * @param position Position in EntryQueue.
	 * @return Car object.
	 */
	public Car newCar(Integer position)
	{
		Integer R = new Integer((new Random()).nextInt(226) + 30);
		Integer G = new Integer((new Random()).nextInt(226) + 30);
		Integer B = new Integer((new Random()).nextInt(226) + 30);

		Car car = new Car(new Color(R, G, B), position);

		car.start();

		cars.add(car);

		return car;
	}

    /**
     * Method creates new Car object.
     * 
     * @return Car object.
     */
	public Car newCar()
	{
	    return newCar(0);
	}

	/**
	 * Method notifies all cars - it says "wake up boys".
	 */
	public void notifyCars()
	{
	    Car car;

		for (int i = 0, j = cars.size(); i < j; i++) {
		    car = cars.get(i);
			synchronized (car) {
			    car.notify();
			}
		}
	}

	/**
	 * Method paint all Car objects in CarsCollection.
	 * 
	 * @param g Graphics parameter.
	 */
	public void paint(Graphics g)
	{
		for (int i = 0, j = cars.size(); i < j; i++) {
			cars.get(i)
				.paint(g);
		}
	}
}
