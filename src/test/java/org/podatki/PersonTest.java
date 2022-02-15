package org.podatki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PersonTest {
  public static final BigDecimal SALARY = new BigDecimal("15000");
  @Mock private TaxesOption taxesOption;

  static Stream<Arguments> testMonthlySalary() {
    return Stream.of(
        arguments(
            "Jan",
            "Kowalski",
            new BigDecimal("15000.0"),
            new Married(),
            new BigDecimal("36000.00")),
        arguments(
            "Janina", "Nowak", new BigDecimal("15000.0"), new Single(), new BigDecimal("72000.00")),
        arguments(
            "Kasia", "Wójcik", new BigDecimal("15000.0"), new Parent(), new BigDecimal("48000.00")),
        arguments(
            "Marcin", "Test", new BigDecimal("1000.0"), new Parent(), new BigDecimal("7000")));
  }

  static Stream<Arguments> testYearlyIncome() {
    return Stream.of(arguments("Joanna", "Test", new BigDecimal("10000"), new BigDecimal("5000")));
  }

  static List<BigDecimal> testSmallSalary() {
    List<BigDecimal> salaries = new ArrayList<>();
    salaries.add(new BigDecimal("1000"));
    return salaries;
  }

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @ParameterizedTest(name = "{index} person {0} {1}({3}) with salary {2} should pay tax {4}")
  @MethodSource("testMonthlySalary")
  public void shouldPersonWithSalaryPayCorrespondingAnnualTax(
      String name, String surname, BigDecimal salary, TaxesOption taxesOption, BigDecimal tax) {
    // given
    Person person = new Person(name, surname, salary, taxesOption);

    // when
    var annualTaxMarried = person.payTax();

    // then
    Assertions.assertEquals(tax, annualTaxMarried);
  }

  @Test
  public void shouldCorrectlyCalculateYearlyIncome() {
    // given
    Person person = new Person("Joanna", "Test", new BigDecimal(10000), new TestTaxesOption());
    // when
    var annualTax = person.payTax();
    // then
    Assertions.assertEquals(new BigDecimal(10000 * 12), annualTax);
  }

  @ParameterizedTest(name = "{index} person {0} {1}with salary {2} should pay annual tax {3}")
  @MethodSource("testYearlyIncome")
  public void shouldCorrectlyCalculateYearlyIncomeWithMock(
      String name, String surname, BigDecimal salary, BigDecimal tax) {

    // given
    when(taxesOption.payTaxes(eq(new BigDecimal(10000 * 12)))).thenReturn(tax);
    Person person = new Person(name, surname, salary, taxesOption);
    // when
    var annualTax = person.payTax();
    // then
    Assertions.assertEquals(tax, annualTax);
  }
}
