package edu.cpp.CYS;

import edu.cpp.CYS.controller.MathController;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {

    //simple math test for JUnit testing
    @Test
    public void testMultiply()
    {
        MathController mathController = new MathController();
        double result = mathController.multiply(3, 10);
        Assert.assertEquals(30.0, result, 0.0);
    }
}
