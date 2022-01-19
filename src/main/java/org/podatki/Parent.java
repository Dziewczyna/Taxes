package org.podatki;

import java.math.BigDecimal;

public class Parent implements TaxesOption {
  @Override
  public BigDecimal payTaxes(BigDecimal annualSalary) {
    return annualSalary
        .multiply(new BigDecimal("0.3"))
        .subtract(new BigDecimal(12 * 500))
        .max(new BigDecimal("7000"));
  }
}
