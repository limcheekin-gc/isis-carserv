/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.fixture.vehicle;

import com.pragprog.dhnako.carserv.dom.customer.Customer;

public class JoeBloggsCarsFixture extends AbstractCarFixture {
    public void install() {
    	Customer joeBloggs = findCustomer("Bloggs", "Joe");
    	createCar(joeBloggs, findModel(findMake("Ford"), "Focus"), "M321RNP");
    	createCar(joeBloggs, findModel(findMake("Toyota"), "Yaris"), "KL56WGF");
    }
}
