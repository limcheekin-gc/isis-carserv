/***
 * Excerpted from "Domain-Driven Design Using Naked Objects",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/dhnako for more book information.
***/
package com.pragprog.dhnako.carserv.fixture.vehicle;

import org.apache.isis.applib.fixtures.AbstractFixture;

import com.pragprog.dhnako.carserv.dom.vehicle.Make;
import com.pragprog.dhnako.carserv.dom.vehicle.Model;

public class MakesAndModelsFixture extends AbstractFixture {


    public void install() {
        Make fordMake = createMake("Ford");
        Make toyotaMake = createMake("Toyota");
        createModel(fordMake, "Focus");
        createModel(fordMake, "Mustang");
        createModel(toyotaMake, "Corolla");
        createModel(toyotaMake, "Yaris");
    }
    private Make createMake(String name) {
        Make make = newTransientInstance(Make.class);
        make.setName(name);
        persist(make);
        return make;
    }
    private Model createModel(Make make, String name) {
        Model model = newTransientInstance(Model.class);
        model.setName(name);
        make.addToModels(model);
        persist(model);
        return model;
    }

}
