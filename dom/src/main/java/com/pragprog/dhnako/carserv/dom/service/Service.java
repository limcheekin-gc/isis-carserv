/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.service;

import java.sql.Date;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.MemberOrder;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
public class Service extends AbstractDomainObject {
	// {{ BookedIn
	private Date bookedIn;
	@MemberOrder(sequence = "1")
	public Date getBookedIn() {
		return bookedIn;
	}

	public void setBookedIn(Date bookedIn) {
		this.bookedIn = bookedIn;
	}
	// }}
	
	// {{ EstimatedReady
	private Date estimatedReady;
	@MemberOrder(sequence = "2")
	public Date getEstimatedReady() {
		return estimatedReady;
	}

	public void setEstimatedReady(Date estimatedReady) {
		this.estimatedReady = estimatedReady;
	}
	// }}

}
