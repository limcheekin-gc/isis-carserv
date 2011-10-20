/**
 * 
 */
package com.pragprog.dhnako.carserv.dom.customer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.isis.applib.AbstractDomainObject;
import org.apache.isis.applib.annotation.MaxLength;
import org.apache.isis.applib.annotation.MemberOrder;
import org.apache.isis.applib.annotation.DescribedAs;
import org.apache.isis.applib.annotation.MultiLine;
import org.apache.isis.applib.annotation.Optional;
import org.apache.isis.applib.util.TitleBuffer;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
public class Customer extends AbstractDomainObject {
	
	// {{ Identification
	public String title() {
		TitleBuffer buf = new TitleBuffer();
		buf.append(getTitle());
		buf.append(getFirstName()).append(getLastName());
		return buf.toString();
	}
	
	public String iconName() {
		if ("Mr".equals(getTitle())) return "Man";
		if ("Mrs".equals(getTitle())) return "Woman";
		if ("Ms".equals(getTitle())) return "Woman";
		if ("Miss".equals(getTitle())) return "Woman";
		return null; // default
	}
	// }}
	
	// {{ Title
	private String title;
	private static final List<String> TITLES = Arrays.asList("Mr", "Mrs", "Ms", "Miss");

	@MemberOrder(sequence = "1.1")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> choicesTitle() {
		return TITLES;
	}

	public String validateTitle(String title) {
		if (title == null) return null;
		if (!TITLES.contains(title)) {
			return "Title must be one of " + TITLES;
		}
		return null;
	}
	// }}
	
	// {{ FirstName
	private String firstName;

	@MemberOrder(sequence = "1.2")
	@DescribedAs("The name given to this customer, or by which he/she is known")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	// }}

	// {{ LastName
	private String lastName;

	@MemberOrder(sequence = "1.3")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	// }}

	// {{ Notes
	private String notes;

	@MultiLine(numberOfLines = 10, preventWrapping = false)
	@Optional
	@MaxLength(255)
	@MemberOrder(sequence = "1.4")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	// }}



}
