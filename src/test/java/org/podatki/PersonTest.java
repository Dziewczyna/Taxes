package org.podatki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    public void shouldMarriedPersonWithSalary15000payAnnualTax36000(){
        //given
        Person married = new Person("Jan", "Kowalski", new BigDecimal("15000"), new Married());

        //when
        var annualTaxMarried = married.payTax();

        //then
        Assertions.assertEquals(annualTaxMarried, new BigDecimal("36000.0"));
    }

    @Test
    public void shouldSinglePersonWithSalary15000payAnnualTax72000(){

        //given
        Person single = new Person("Janina", "Nowak", new BigDecimal("15000"), new Single());

        //when
        var annualTaxSingle = single.payTax();

        //then
        Assertions.assertEquals(annualTaxSingle, new BigDecimal("72000.0"));
    }

    @Test
    public void shouldMotherWithSalary15000payAnnualTax48000(){

        //given
        Person mother = new Person("Kasia", "Wójcik", new BigDecimal("15000"), new Parent());

        //when
        var annualTaxParent = mother.payTax();

        //then
        Assertions.assertEquals(annualTaxParent, new BigDecimal("48000.0"));
    }

    @Test
    public void shouldFatherWithSalary1000payAnnualTax7000(){
        //given
        Person father = new Person("Marcin", "Test", new BigDecimal("1000"), new Parent());

        //when
        var annualTaxFather = father.payTax();

        //then
        Assertions.assertEquals(annualTaxFather, new BigDecimal("7000"));
    }
}