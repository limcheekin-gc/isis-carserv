/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.customer;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Debug;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.Named;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.security.UserMemento;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 *
 */
@Named("Customers")
public class CustomerRepository extends AbstractFactoryAndRepository {
	// {{ Create new (still transient) Customer
	public Customer newCustomer() {
	    Customer customer = newTransientInstance(Customer.class);
	    return customer;
	}
	// }}
	
	// {{ all Customers
	@Exploration
	public List<Customer> allCustomers() {
	    return allInstances(Customer.class);
	}
	// }}	
	
	// {{ Identification
	public String iconName() {
	    return "Customer";
	}
	// }}		
	
	// {{ currentUser
	@Debug
	public UserMemento currentUser() {
		return getUser();
	}
	// }}	
	
	// {{ findByName
	public List<Customer> findByName(
			@Optional @Named("First Name") final String firstName,
			@Optional @Named("Last Name") final String lastName) {
		return allMatches(Customer.class, new Filter<Customer>() {
			public boolean accept(final Customer customer) {
				return matches(customer, firstName, lastName);
			}
		});
	}
	public String validateFindByName(
	        final String lastName, final String firstName) {
	    if (lastName == null && firstName == null) {
	        return "Must specify at least one name";
	    }
	    return null;
	}
	private static boolean matches(
	       final Customer customer, 
	       final String firstName, final String lastName) {
	    return nullSafeEquals(customer.getFirstName(), firstName) &&
	           nullSafeEquals(customer.getLastName(), lastName);
	}
	private static <T> boolean nullSafeEquals(final T s1, final T s2) {
	    return s1 == null || s2 == null ||
	           s1 != null && s2 != null && s1.equals(s2);
	}
  // }}	
}
