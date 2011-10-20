/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
 ***/
package com.pragprog.dhnako.carserv.dom.customer;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.util.TitleBuffer;

@Bounded
public class Title extends AbstractDomainObject {

	// {{ Identification
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(getName());
		return buf.toString();
	}
	
	public String iconName() {
		return getIconName();
	}
	// }}

	// {{ Name
	private String name;

	@MemberOrder(sequence = "1.1")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// }}

	// {{ IconName
	private String iconName;

	@MemberOrder(sequence = "1.2")
	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	// }}

}
