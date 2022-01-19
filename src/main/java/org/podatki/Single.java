package org.podatki;

import java.math.BigDecimal;

public class Single implements TaxesOption{
    @Override
    public BigDecimal payTaxes(BigDecimal annualSalary) {
        return annualSalary.multiply(new BigDecimal("0.4"));
    }
}
