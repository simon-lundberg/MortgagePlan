import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MortgagePlanTest {

    @Test
    public void testMonthlyPayment(){

        double actualVal = MortgagePlan.monthlyPayment(1000, 5,2);
        String expectedVal = "43.87";
        Assertions.assertEquals(expectedVal, twoDecimals(actualVal));

        actualVal = MortgagePlan.monthlyPayment(4356, 1.27,6);
        expectedVal = "62.87";
        Assertions.assertEquals(expectedVal, twoDecimals(actualVal));

        actualVal = MortgagePlan.monthlyPayment(1234, 1.234,10);
        expectedVal = "10.94";
        Assertions.assertEquals(expectedVal, twoDecimals(actualVal));

        actualVal = MortgagePlan.monthlyPayment(550000, 2.40,15);
        expectedVal = "3641.51";
        Assertions.assertEquals(expectedVal, twoDecimals(actualVal));

    }

    @Test
    public void testMonthlyPaymentNotEqual(){

        double actualVal = MortgagePlan.monthlyPayment(1300.55, 8.67,2);
        String expectedVal = "150.56";
        Assertions.assertNotEquals(expectedVal, twoDecimals(actualVal));

        actualVal = MortgagePlan.monthlyPayment(2000, 6,4);
        expectedVal = "97.14";
        Assertions.assertNotEquals(expectedVal, twoDecimals(actualVal));

        actualVal = MortgagePlan.monthlyPayment(4321, 4.321,10);
        expectedVal = "44.44";
        Assertions.assertNotEquals(expectedVal, twoDecimals(actualVal));

        actualVal = MortgagePlan.monthlyPayment(250000, 3.15,15);
        expectedVal = "25.39";
        Assertions.assertNotEquals(expectedVal, twoDecimals(actualVal));
    }

    /**
     * takes a double and returns it with two decimals (as a string)
     * @param in the monthly payment
     * @return the monthly payment with only two decimals
     */
    private String twoDecimals(final double in){
        return String.format("%.2f",in);
    }
}
