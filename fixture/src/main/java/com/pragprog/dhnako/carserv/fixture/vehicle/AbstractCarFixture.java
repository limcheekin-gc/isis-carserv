/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.fixture.vehicle;

import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.fixtures.AbstractFixture;

import com.pragprog.dhnako.carserv.dom.customer.Customer;
import com.pragprog.dhnako.carserv.dom.vehicle.Car;
import com.pragprog.dhnako.carserv.dom.vehicle.Make;
import com.pragprog.dhnako.carserv.dom.vehicle.Model;

public abstract class AbstractCarFixture extends AbstractFixture {

    public abstract void install();
    
    protected Car createCar(
    		final Customer customer, 
    		final Model model, 
    		final String registrationNumber) {
    	Car car = newTransientInstance(Car.class);
        car.modifyOwningCustomer(customer);
        car.setModel(model);
        car.setRegistrationNumber(registrationNumber);
        persist(car);
        return car;
    }

	protected Make findMake(final String name) {
		return firstMatch(Make.class, new Filter<Make>() {
			public boolean accept(Make make) {
				return make.getName().equals(name);
			}
    	});
	}

	protected Model findModel(final Make make, final String name) {
		return firstMatch(Model.class, new Filter<Model>() {
			public boolean accept(Model model) {
				return model.getMake() == make &&
				       model.getName().equals(name);
			}
    	});
	}

	protected Customer findCustomer(final String lastName, final String firstName) {
		return firstMatch(Customer.class, new Filter<Customer>() {
			public boolean accept(Customer customer) {
				return customer.getLastName().equals(lastName) &&
				       customer.getFirstName().equals(firstName);
			}
    	});
	}
}
