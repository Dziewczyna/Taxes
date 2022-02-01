package org.podatki;

import java.math.BigDecimal;

public class TestTaxesOption implements TaxesOption{

    @Override
    public BigDecimal payTaxes(BigDecimal annualSalary) {
        return annualSalary;
    }
}
