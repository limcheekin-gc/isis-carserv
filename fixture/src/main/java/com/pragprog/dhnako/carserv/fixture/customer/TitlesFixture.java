/**
 * 
 */
package com.pragprog.dhnako.carserv.fixture.customer;

import org.apache.isis.applib.fixtures.AbstractFixture;

import com.pragprog.dhnako.carserv.dom.customer.Title;

/**
 * @author <a href='mailto:limcheekin@vobject.com'>Lim Chee Kin</a>
 * 
 */
public class TitlesFixture extends AbstractFixture {
    public void install() {
        createTitle("Mr", "Man");
        createTitle("Mrs", "Woman");
        createTitle("Ms", "Woman");
        createTitle("Miss", "Woman");
    }
    private void createTitle(final String name, final String iconName) {
        Title title = newTransientInstance(Title.class);
        title.setName(name);
        title.setIconName(iconName);
        persist(title);
    }
}
