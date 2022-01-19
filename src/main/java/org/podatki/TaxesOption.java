package org.podatki;

import java.math.BigDecimal;

public interface TaxesOption {
    BigDecimal payTaxes(BigDecimal annualSalary);
}
