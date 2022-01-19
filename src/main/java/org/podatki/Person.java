package org.podatki;

import java.math.BigDecimal;

public class Person{
    private String name;
    private String lastName;
    private BigDecimal salary;
    private TaxesOption taxesOption;

    public Person(String name, String lastName, BigDecimal salary, TaxesOption taxesOption) {
        this.name = name;
        this.lastName = lastName;
        this.salary = salary;
        this.taxesOption = taxesOption;
    }

    protected BigDecimal payTax(){
        return taxesOption.payTaxes(salary.multiply(new BigDecimal(12)));
    }
}
