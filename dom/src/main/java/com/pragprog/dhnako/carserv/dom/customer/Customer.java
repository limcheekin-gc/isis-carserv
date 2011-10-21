/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.customer;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.When;
import org.apache.isis.applib.clock.Clock;
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
	
	public void modifyNotes(final String notes) {
		String currentNotes = getNotes();
		// check for no-op
		if (notes == null || notes.equals(currentNotes)) {
			return;
		}
		// associate new
		setNotes(notes);
		// additional business logic
		onModifyNotes(currentNotes, notes);
	}

	public void clearNotes() {
		String currentNotes = getNotes();
		// check for no-op
		if (currentNotes == null) {
			return;
		}
		// dissociate existing
		setNotes(null);
		// additional business logic
		onClearNotes(currentNotes);
	}

	protected void onModifyNotes(final String oldNotes, final String newNotes) {
		setNotedBy(getUser().getName());
	}

	protected void onClearNotes(final String oldNotes) {
	}
	
	public String disableNotes() {
	    return doesntOwnAnyCars()?"Can only add notes for customers with cars":null;
	}	
	// }}

	private boolean doesntOwnAnyCars() {
		  return getCars().size() == 0;
	}
	
	// {{ NotedBy
	private String notedBy;

	@MemberOrder(sequence = "1.5.5")
	@Optional
	@MaxLength(16)
	public String getNotedBy() {
		return notedBy;
	}

	public void setNotedBy(final String notedBy) {
		this.notedBy = notedBy;
	}
	// }}
	
	// {{ Cars
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
   // }}
	
	// {{ Feedback
	private String feedback;

	@MemberOrder(sequence = "1.6")
	@MultiLine(numberOfLines=5, preventWrapping=false)
	@Optional
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(final String feedback) {
		this.feedback = feedback;
	}

	public boolean hideFeedback() {
		return !isValuableCustomer();
	}
	// }}
	
	private boolean isValuableCustomer() {
	    return getCars().size() >= 2;
	}
	
	// {{ Since
	private Date since;

	@MemberOrder(sequence = "1.7")
	public Date getSince() {
		return since;
	}

	public void setSince(final Date since) {
		this.since = since;
	}
	
	public Date defaultSince() {
		return new java.sql.Date(Clock.getTime());
	}
	// }}	
	
	// {{ newCar
	@MemberOrder(sequence = "1.1")
	@Disabled(When.UNTIL_PERSISTED)
	public Car newCar() {
		Car car = newTransientInstance(Car.class);
		car.setOwningCustomer(this);
		return car;
	}
	// }}

	// {{ deleteCar
	@MemberOrder(sequence = "1.2")
	@Disabled(When.UNTIL_PERSISTED)
	public void deleteCar(final Car car) {
	    car.delete();
	}
	public String validateDeleteCar(final Car car) {
	    return getCars().contains(car) ?
	        null :"Customer does not own this car";
	}	
	public List<Car> choices0DeleteCar() {
		return getCars();
	}
	public Car default0DeleteCar() {
		return getCars().size() == 1? getCars().get(0): null;
	}
	public String disableDeleteCar() {
	    return doesntOwnAnyCars()? "No cars to delete": null;
	}	
	// }}

	boolean matches(final String firstName, final String lastName) {
		return nullSafeEquals(getFirstName(), firstName)
				&& nullSafeEquals(getLastName(), lastName);
	}

	private <T> boolean nullSafeEquals(final T s1, final T s2) {
		return s1 == null || s2 == null || s1 != null && s2 != null
				&& s1.equals(s2);
	}
}

