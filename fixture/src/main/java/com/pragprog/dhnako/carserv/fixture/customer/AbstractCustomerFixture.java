/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.fixture.customer;


import org.apache.isis.applib.filter.Filter;
import org.apache.isis.applib.fixtures.AbstractFixture;

import com.pragprog.dhnako.carserv.dom.customer.Customer;
import com.pragprog.dhnako.carserv.dom.customer.Title;

public abstract class AbstractCustomerFixture extends AbstractFixture {

    public abstract void install();
    
    protected Customer createCustomer(
    		final String titleName, 
    		final String lastName, final String firstName) {
        Customer customer = newTransientInstance(Customer.class);
        customer.setTitle(findTitle(titleName));
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        persist(customer);
        return customer;
    }

	protected Title findTitle(final String titleName) {
		return firstMatch(Title.class, new Filter<Title>() {
			public boolean accept(Title title) {
				return title.getName().equals(titleName);
			}
        });
	}
}
