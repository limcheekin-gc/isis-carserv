/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.vehicle;

import java.util.Locale;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.util.TitleBuffer;

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
	
	@MemberOrder(sequence = "1")
	@MaxLength(12)
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	
	public String validateRegistrationNumber(String registrationNumber) {
	    if (registrationNumber == null) return null;
	    String country = Locale.getDefault().getCountry();
	    int length = registrationNumber.length();
	    if ( ("US".equals(country) && length > 7) ||
	         ("GB".equals(country) && length > 7 ) ||
	         length > 12) { // everywhere else
	        return "Registration number is too long";
	    }
	    return null;
	}
	// }}
}
