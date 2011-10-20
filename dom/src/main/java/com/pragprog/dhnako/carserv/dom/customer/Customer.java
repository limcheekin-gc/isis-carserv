/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.util.TitleBuffer;

import com.pragprog.dhnako.carserv.dom.vehicle.Car;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
public class Customer extends AbstractDomainObject {
	
	// {{ Identification
	public String title() {
		TitleBuffer buf = new TitleBuffer();
		buf.append(getTitle());
		buf.append(getFirstName()).append(getLastName());
		return buf.toString();
	}
	
	public String iconName() {
	    return getTitle() != null? getTitle().getIconName(): null;
	}
	// }}
	
	// {{ Title
	private Title title;

	@MemberOrder(sequence = "1.1")
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
	// }}
	
	// {{ FirstName
	private String firstName;

	@MemberOrder(sequence = "1.2")
	@DescribedAs("The name given to this customer, or by which he/she is known")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	// }}

	// {{ LastName
	private String lastName;

	@MemberOrder(sequence = "1.3")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	// }}

	// {{ Notes
	private String notes;

	@MultiLine(numberOfLines = 10, preventWrapping = false)
	@Optional
	@MaxLength(255)
	@MemberOrder(sequence = "1.4")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	// }}
	
	private List<Car> cars = new ArrayList<Car>();
	@MemberOrder(sequence = "1.5")
	public List<Car> getCars() {
	    return cars;
	}
	public void setCars(final List<Car> cars) {
	    this.cars = cars;
	}		
	
	public void addToCars(final Car car) {
	    // check for no-op
	    if (car == null || getCars().contains(car)) {
	        return;
	    }
	    // dissociate arg from its current parent (if any).
	    car.clearOwningCustomer();
	    // associate arg
	    car.setOwningCustomer(this);
	    getCars().add(car);
	    // additional business logic
	    onAddToCars(car);
	}
	public void removeFromCars(
	        final Car car) {
	    // check for no-op
	    if (car == null || !getCars().contains(car)) {
	        return;
	    }
	    // dissociate arg
	    car.setOwningCustomer(null);
	    // dissociate existing
	    getCars().remove(car);
	    // additional business logic
	    onRemoveFromCars(car);
	}
	protected void onAddToCars(final Car car) {
	}
	protected void onRemoveFromCars(final Car car) {
	}	
	
	public String validateAddToCars(final Car car) {
		return getCars().size() >= 3?
				"No more than 3 cars per customer": null;
	}

	public String validateRemoveFromCars(final Car car) {
		return null;
	}		

}
