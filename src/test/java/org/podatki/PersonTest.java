package org.podatki;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PersonTest {
    @Mock
    private TaxesOption taxesOption;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @ParameterizedTest
    @MethodSource("testMonthlySalary")
    public void shouldPersonWithSalary15000payAnnualTax36000(BigDecimal salary){
        //given
        Person married = new Person("Jan", "Kowalski", salary, new Married());

        //when
        var annualTaxMarried = married.payTax();

        //then
        Assertions.assertEquals(annualTaxMarried, new BigDecimal("36000.0"));
    }

    static List<BigDecimal> testMonthlySalary() {
        List<BigDecimal> salaries = new ArrayList<>();
        salaries.add(new BigDecimal("15000"));
        return salaries;
    }

    static List<BigDecimal> testSmallSalary() {
        List<BigDecimal> salaries = new ArrayList<>();
        salaries.add(new BigDecimal("1000"));
        return salaries;
    }

    @ParameterizedTest
    @MethodSource("testMonthlySalary")
    public void shouldSinglePersonWithSalary15000payAnnualTax72000(BigDecimal salary){

        //given
        Person single = new Person("Janina", "Nowak", salary, new Single());

        //when
        var annualTaxSingle = single.payTax();

        //then
        Assertions.assertEquals(annualTaxSingle, new BigDecimal("72000.0"));
    }

    @ParameterizedTest
    @MethodSource("testMonthlySalary")
    public void shouldMotherWithSalary15000payAnnualTax48000(BigDecimal salary){

        //given
        Person mother = new Person("Kasia", "Wójcik", salary, new Parent());

        //when
        var annualTaxParent = mother.payTax();

        //then
        Assertions.assertEquals(annualTaxParent, new BigDecimal("48000.0"));
    }

    @ParameterizedTest
    @MethodSource("testSmallSalary")
    public void shouldFatherWithSalary1000payAnnualTax7000(BigDecimal salary){
        //given
        Person father = new Person("Marcin", "Test", salary, new Parent());

        //when
        var annualTaxFather = father.payTax();

        //then
        Assertions.assertEquals(annualTaxFather, new BigDecimal("7000"));
    }

    @Test
    public void shouldCorrectlyCalculateYearlyIncome(){
        //given
        Person person = new Person("Joanna","Test",new BigDecimal(10000), new TestTaxesOption());
        //when
        var annualTax = person.payTax();
        //then
        Assertions.assertEquals(new BigDecimal(10000 * 12),annualTax);
    }

    @Test
    public void shouldCorrectlyCalculateYearlyIncomeWithMock(){

        //given
        when(taxesOption.payTaxes(eq(new BigDecimal(10000*12)))).thenReturn(new BigDecimal(5000));
        Person person = new Person("Joanna","Test",new BigDecimal(10000), taxesOption);
        //when
        var annualTax = person.payTax();
        //then
        Assertions.assertEquals(new BigDecimal(5000),annualTax);

    }
}