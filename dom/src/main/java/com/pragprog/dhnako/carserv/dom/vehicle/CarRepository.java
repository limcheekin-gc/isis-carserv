/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.vehicle;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.Named;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
@Named("Cars")
public class CarRepository extends AbstractFactoryAndRepository {
	// {{ Create new (still transient) Car
	public Car newCar() {
		Car car = newTransientInstance(Car.class);
		return car;
	}
	// }}
	
	// {{ all Cars
	@Exploration
	public List<Car> allCars() {
	    return allInstances(Car.class);
	}
	// }}	
	
	// {{ Identification
	public String iconName() {
	    return "Car";
	}
	// }}			
}
