/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.dom.vehicle;

import java.util.ArrayList;
import java.util.List;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.Bounded;
import org.apache.isis.applib.annotation.Immutable;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.util.TitleBuffer;

@Bounded
@Immutable
public class Make extends AbstractDomainObject {

	
	// {{ Identification
	public String title() {
		final TitleBuffer buf = new TitleBuffer();
		buf.append(getName());
		return buf.toString();
	}
	// }}


	// {{ Name
	private String name;

	@MemberOrder(sequence = "1.1")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}
	// }}
	

	
	// {{ Models
	private List<Model> models = new ArrayList<Model>();

	@MemberOrder(sequence = "1.2")
	public List<Model> getModels() {
		return models;
	}

	public void setModels(final List<Model> models) {
		this.models = models;
	}

	public void addToModels(final Model model) {
		// check for no-op
		if (model == null
				|| getModels().contains(model)) {
			return;
		}
		// dissociate arg from its current parent (if any).
		model.clearMake();
		// associate arg
		model.setMake(this);
		getModels().add(model);
		// additional business logic
		onAddToModels(model);
	}

	public void removeFromModels(
			final Model model) {
		// check for no-op
		if (model == null
				|| !getModels().contains(model)) {
			return;
		}
		// dissociate arg
		model.setMake(null);
		getModels().remove(model);
		// additional business logic
		onRemoveFromModels(model);
	}

	protected void onAddToModels(final Model model) {
	}

	protected void onRemoveFromModels(final Model model) {
	}
	// }}

}
