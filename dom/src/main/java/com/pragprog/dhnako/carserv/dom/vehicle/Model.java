/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.dom.vehicle;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Immutable;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.util.TitleBuffer;

@Bounded
@Immutable
public class Model extends AbstractDomainObject {

	
	// {{ Identification
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(getName());
		return buf.toString();
	}
	// }}


	// {{ Name
	private String name;

	@MemberOrder(sequence = "1.2")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	// }}


	// {{ Make
	private Make make;

	@MemberOrder(sequence = "1.1")
	public Make getMake() {
		return make;
	}

	public void setMake(final Make make) {
		this.make = make;
	}

	public void modifyMake(final Make make) {
		Make currentMake = getMake();
		// check for no-op
		if (make == null || currentMake == make) {
			return;
		}
		// delegate to parent to associate
		make.addToModels(this);
		// additional business logic
		onModifyMake(currentMake, make);
	}

	public void clearMake() {
		Make currentMake = getMake();
		// check for no-op
		if (currentMake == null) {
			return;
		}
		// delegate to parent to dissociate
		currentMake.removeFromModels(this);
		// additional business logic
		onClearMake(currentMake);
	}

	protected void onModifyMake(final Make oldMake, final Make newMake) {
	}

	protected void onClearMake(final Make oldMake) {
	}
	// }}


}
