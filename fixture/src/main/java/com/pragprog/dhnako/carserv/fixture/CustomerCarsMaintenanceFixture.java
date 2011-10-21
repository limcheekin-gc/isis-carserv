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

public class CustomerCarsMaintenanceFixture extends AbstractFixture {
	
	public CustomerCarsMaintenanceFixture() {
		addFixture(new LogonAsFredSmithFixture());
		addFixture(new DateIs23Sep1988Fixture());
		addFixture(new ReferenceDataFixture());
		addFixture(new CustomerCarsMaintenanceTransactionalFixture());
		addFixture(new DateIs09Jul2008Fixture());
	}

}
