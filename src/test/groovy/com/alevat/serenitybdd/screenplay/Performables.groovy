package com.alevat.serenitybdd.screenplay

import net.serenitybdd.screenplay.Actor
import net.serenitybdd.screenplay.Performable

class Performables {

    static Performable and() {
        return new Performable() {
            @Override
            <T extends Actor> void performAs(T actor) {
                // syntactic sugar
            }
        }
    }

}
