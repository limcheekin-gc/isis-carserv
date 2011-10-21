/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.fixture;

import org.apache.isis.applib.fixtures.AbstractFixture;

import com.pragprog.dhnako.carserv.fixture.customer.JoeBloggsCustomerFixture;
import com.pragprog.dhnako.carserv.fixture.vehicle.JoeBloggsCarsFixture;

public class CustomerCarsMaintenanceTransactionalFixture extends AbstractFixture {
	
	public CustomerCarsMaintenanceTransactionalFixture() {
		addFixture(new JoeBloggsCustomerFixture());
		addFixture(new JoeBloggsCarsFixture());
	}

}
