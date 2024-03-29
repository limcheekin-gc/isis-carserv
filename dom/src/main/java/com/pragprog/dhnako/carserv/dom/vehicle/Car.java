/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.vehicle;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.Disabled;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.annotation.RegEx;
import org.apache.isis.applib.annotation.When;
import org.apache.isis.applib.util.TitleBuffer;

import com.pragprog.dhnako.carserv.dom.customer.Customer;
import com.pragprog.dhnako.carserv.dom.service.Service;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
public class Car extends AbstractDomainObject {
	// {{ Identification
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(getRegistrationNumber());
		return buf.toString();
	}
	// }}	
	
	// {{ RegistrationNumber
	private String registrationNumber;
	
	@MemberOrder(sequence = "1.1")
	@MaxLength(12)
	@RegEx(validation="[A-Z0-9]+")
	// TODO: Error @MustSatisfy(RegistrationNumberSpecification.class)
	@Disabled(When.ONCE_PERSISTED)
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	// }}
	
	// {{ OwningCustomer
	private Customer owningCustomer;

	@Optional
	@MemberOrder(sequence = "1.2")
	@Disabled
	public Customer getOwningCustomer() {
		return owningCustomer;
	}

	public void setOwningCustomer(final Customer owningCustomer) {
		this.owningCustomer = owningCustomer;
	}

	public void modifyOwningCustomer(final Customer owningCustomer) {
		Customer currentOwningCustomer = getOwningCustomer();
		// check for no-op
		if (owningCustomer == null || owningCustomer.equals(currentOwningCustomer) ) {
			return;
		}
		// delegate to parent to associate
		owningCustomer.addToCars(this);
		// additional business logic
		onModifyOwningCustomer(currentOwningCustomer, owningCustomer);
	}

	public void clearOwningCustomer() {
		Customer currentOwningCustomer = getOwningCustomer();
		// check for no-op
		if (currentOwningCustomer == null) {
			return;
		}
		// delegate to parent to dissociate
		currentOwningCustomer.removeFromCars(this);
		// additional business logic
		onClearOwningCustomer(currentOwningCustomer);
	}

	protected void onModifyOwningCustomer(final Customer oldOwningCustomer, final Customer newOwningCustomer) {
	}

	protected void onClearOwningCustomer(final Customer oldOwningCustomer) {
	}
	
	public String validateOwningCustomer(final Customer owningCustomer) {
		if (owningCustomer != null) {
			return owningCustomer.validateAddToCars(this);
		} else {
			// clearing association
			if (getOwningCustomer() == null) {
				return null;
			}
			return getOwningCustomer().validateRemoveFromCars(this);
		}
	}
	// }}


	// {{ Services
	private List<Service> services = new ArrayList<Service>();

	@MemberOrder(sequence = "1.4")
	@Disabled
	public List<Service> getServices() {
		return services;
	}

	public void setServices(final List<Service> services) {
		this.services = services;
	}

	public void addToServices(final Service service) {
		// check for no-op
		if (service == null
				|| getServices().contains(service)) {
			return;
		}
		// dissociate arg from its current parent (if any).
		service.clearCar();
		// associate arg
		service.setCar(this);
		getServices().add(service);
		// additional business logic
		onAddToServices(service);
	}

	public void removeFromServices(
			final Service service) {
		// check for no-op
		if (service == null
				|| !getServices().contains(service)) {
			return;
		}
		// dissociate arg
		service.setCar(null);
		getServices().remove(service);
		// additional business logic
		onRemoveFromServices(service);
	}

	protected void onAddToServices(final Service service) {
	}

	protected void onRemoveFromServices(final Service service) {
	}
	// }}


	// {{ Model
	private Model model;

	@MemberOrder(sequence = "1.3")
	@Disabled(When.ONCE_PERSISTED)
	public Model getModel() {
		return model;
	}

	public void setModel(final Model model) {
		this.model = model;
	}
	// }}	
	
	@MemberOrder(sequence="1.2")
	@Disabled(When.UNTIL_PERSISTED)
	public void delete() {
	    clearOwningCustomer();
	    remove(this);
	}	
		
	public void persisting() {
	    getOwningCustomer().addToCars(this);
	}	
	
	@MemberOrder(sequence="1.1")
	public Service bookService( ) {
	    Service service = newTransientInstance(Service.class);
	    service.setCar(this);
	    return service;
	}	
}
