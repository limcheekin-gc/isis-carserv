/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.dom.service;

import java.sql.Date;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.clock.Clock;
import org.apache.isis.applib.util.TitleBuffer;

import com.pragprog.dhnako.carserv.dom.vehicle.Car;

public class Service extends AbstractDomainObject {


	// {{ Identification
	public String title() {
        TitleBuffer buf = new TitleBuffer();
        if (getCar() != null) {
	        buf.append(getCar().getRegistrationNumber());
        }
        buf.append(":", getBookedIn());
        return buf.toString();
	}
	// }}

	
	// {{ BookedIn
	private Date bookedIn;

	@MemberOrder(sequence = "1.1")
	public Date getBookedIn() {
		return bookedIn;
	}

	public void setBookedIn(final Date bookedIn) {
		this.bookedIn = bookedIn;
	}
	
	public Date defaultBookedIn() {
	    return todayPlus(1);
	}	
	// }}
	
	
	// {{ EstimatedReady
	private Date estimatedReady;

	@MemberOrder(sequence = "1.2")
	public Date getEstimatedReady() {
		return estimatedReady;
	}

	public void setEstimatedReady(final Date estimatedReady) {
		this.estimatedReady = estimatedReady;
	}
	
	public Date defaultEstimatedReady() {
	    return todayPlus(1);
	}	
	// }}


	// {{ Car
	private Car car;

	@MemberOrder(sequence = "1.3")
	public Car getCar() {
		return car;
	}

	public void setCar(final Car car) {
		this.car = car;
	}

	public void modifyCar(final Car car) {
		Car currentCar = getCar();
		// check for no-op
		if (car == null || car.equals(currentCar)) {
			return;
		}
		// delegate to parent to associate
		car.addToServices(this);
		// additional business logic
		onModifyCar(currentCar, car);
	}

	public void clearCar() {
		Car currentCar = getCar();
		// check for no-op
		if (currentCar == null) {
			return;
		}
		// delegate to parent to dissociate
		currentCar.removeFromServices(this);
		// additional business logic
		onClearCar(currentCar);
	}

	protected void onModifyCar(final Car oldCar, final Car newCar) {
	}

	protected void onClearCar(final Car oldCar) {
	}
	// }}

	public void persisting() {
	    getCar().addToServices(this);
	}
	
	public String validate() {
	    return getEstimatedReady() != null &&
	           getBookedIn()       != null &&
	           getEstimatedReady().getTime() < getBookedIn().getTime() ?
	           "The 'estimated ready' date cannot be before "+
	           "the 'booked in' date"
	           :null;
	}
	
	private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
	private Date todayPlus(final int days) {
	    Date midnight = new Date(Clock.getTime());
	    return new Date(midnight.getTime() + MILLIS_PER_DAY * days); 
	}	


}
