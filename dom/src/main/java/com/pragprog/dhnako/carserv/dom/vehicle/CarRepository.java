/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.vehicle;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.filter.Filter;


/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
@Named("Cars")
public class CarRepository extends AbstractFactoryAndRepository {
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

	public Car findByRegistrationNumber(
	        @RegEx(validation="[A-Z0-9]+")
	        @Named("Registration Number")
	        final String regNumber) {
	    return firstMatch(Car.class, new Filter<Car>() {
	        public boolean accept(final Car car) {
	            return car.getRegistrationNumber().equals(regNumber);
	        }});
	}	
	
}
