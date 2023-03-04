package edu.cpp.CYS.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.springframework.web.bind.annotation.*;

@Controller
@ComponentScan
public class MathController
{
    int[] data = {1,2,3,4,5,6,7,8};

    /*
    * How to create endpoints and differences btw @Controller and @RestController
    * https://stackoverflow.com/questions/72211755/when-to-use-controller-and-when-restcontroller-annotation-in-restapi-based-on
    * Note: @RestController = @Controller + @ResponseBody
     */
    @GetMapping("/math")
    @ResponseBody //add @ResponseBody will connect the end point for the /math URL
    public String math()
    {
        // Get a DescriptiveStatistics instance
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for( int i = 0; i < data.length; i++) {
            stats.addValue(data[i]);
        }
        double mean = stats.getMean();

        return String.format("The means is %s", mean);
    }

}

