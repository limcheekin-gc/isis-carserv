/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.dom.vehicle;

import java.util.Locale;

import org.apache.isis.applib.spec.AbstractSpecification;

public class RegistrationNumberSpecification 
                extends AbstractSpecification<String> {
    @Override
    public String satisfiesSafely(final String registrationNumber) {
        if (registrationNumber == null) return null;
        String country = Locale.getDefault().getCountry();
        int length = registrationNumber.length();
        if ( ("US".equals(country) && length > 7) ||
             ("GB".equals(country) && length > 7) ||
             length > 12) { // everywhere else
            return "Registration number is too long";
        }
        return null;
    }
}

