/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.customer;

import java.util.List;

import org.apache.isis.applib.AbstractFactoryAndRepository;
import org.apache.isis.applib.annotation.Exploration;
import org.apache.isis.applib.annotation.Named;

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
}
