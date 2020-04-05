package com.example.paycalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SalaryTest {

    /*

                            The test is as follows:

        Weekday rates = 5               |
        Saturday rates = 10             |
        Sunday rates = 20               |

        hours worked during week:       |

        Monday = 9                      |       9 x 5 = 45
        Tuesday = 9                     |       9 x 5 = 45
        Wednesday = 9                   |       9 x 5 = 45
        Thursday = 9                    |       9 x 5 = 45
        Friday = 9                      |       9 x 5 = 45

        Saturday = 5                    |       5 x 10 = 50

        Sunday = 2                      |       2 x 20 = 40
                                                -------------
                                                315

     */

    WeeklySalary weekSalary;


    @Test
    public void testCalcWeeklySalary() {
        weekSalary = new WeeklySalary();


        weekSalary.setWeekdayRate("5.0");
        weekSalary.setSatRate("10.0");
        weekSalary.setSunRate("20.0");


        for (int i = 0; i < 5; ++i) {

            weekSalary.setDailyHours("9"); //sets weekday hours

        }
        weekSalary.setDailyHours("5");  // sets saturday hours
        weekSalary.setDailyHours("2");  //sets sunday hours


        // the following tests checks if the all the weekdays are being properly calculated
        for (int i = 0; i < 5; ++i) {

            assertEquals(45, weekSalary.getDailyPay(i), 0);

        }

        assertEquals(50, weekSalary.getDailyPay(5), 0); //test if saturday is being calculated


        assertEquals(40, weekSalary.getDailyPay(6), 0); //test if sunday is being calculated

        assertEquals(315, weekSalary.getWeekSalary(), 0); //test if week is being calculated

        System.out.println("First test: success");


    }

        /*

            This test test that the default values are being called on object creation
                            The test is as follows:

        Weekday rates = 5               |
        Saturday rates = 10             |
        Sunday rates = 20               |

        hours worked during week:       |

        Monday = 9                      |       9 x 38 = 342
        Tuesday = 9                     |       9 x 38 = 342
        Wednesday = 9                   |       9 x 38 = 342
        Thursday = 9                    |       9 x 38 = 342
        Friday = 9                      |       9 x 38 = 342

        Saturday = 5                    |       5 x 57 = 285

        Sunday = 2                      |       2 x 78 = 156
                                                -------------
                                                2210

     */

    @Test
    public void testDefaultRates() {
        weekSalary = new WeeklySalary();

        for (int i = 0; i < 5; ++i) {

            weekSalary.setDailyHours("9"); //sets weekday hours

        }
        weekSalary.setDailyHours("5");  // sets saturday hours
        weekSalary.setDailyHours("2");  //sets sunday hours


        // the following tests checks if the all the weekdays are being properly calculated
        for (int i = 0; i < 5; ++i) {

            assertEquals(342, weekSalary.getDailyPay(i), 0);

        }
        assertEquals(285, weekSalary.getDailyPay(5), 0); //test if saturday is being calculated


        assertEquals(156, weekSalary.getDailyPay(6), 0); //test if sunday is being calculated

        assertEquals(2151, weekSalary.getWeekSalary(), 0); //test if week is being calculated

        System.out.println("Second test: success - default values are being called on creation");


    }


    @Test
    public void testDecimalRates() {
        weekSalary = new WeeklySalary();


        /*
        This test checks if the rates can process decimal rates and entered hours
 */

        weekSalary.setWeekdayRate("5.2");
        weekSalary.setSatRate("10.72");
        weekSalary.setSunRate("20.33");

        for (int i = 0; i < 5; ++i) {

            weekSalary.setDailyHours("9.22"); //sets weekday hours

        }
        weekSalary.setDailyHours("5.5");  // sets saturday hours
        weekSalary.setDailyHours("2.2");  //sets sunday hours


        // the following tests checks if the all the weekdays are being properly calculated
        for (int i = 0; i < 5; ++i) {

            assertEquals(47.94, weekSalary.getDailyPay(i), 0.01); //deltas are all set to accept a difference of 0.01

        }
        assertEquals(58.96, weekSalary.getDailyPay(5), 0.01);


        assertEquals(44.73, weekSalary.getDailyPay(6), 0.01);

        assertEquals(343.39, weekSalary.getWeekSalary(), 0.01);

        System.out.println("Third test: success - Decimal values are correctly returned");

        System.out.println("----------------------------------------------------------------------");

        System.out.println("Tests also show that WeeklySalary takes in string values and returns floats");


    }


}