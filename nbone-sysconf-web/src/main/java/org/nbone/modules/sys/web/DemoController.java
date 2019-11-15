package org.nbone.modules.sys.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-11-15
 */
//"#{${mongodb.url:dict}.split(',')}"
@RestController
@RequestMapping(value = {"${demo0.url:}","${demo1.url:}","${demo2.url:}"})
public class DemoController {

    @Value("#{'${demo0.url}'.split(',')}")
    private String[] value;



    @RequestMapping(value = {"list", ""})
    public List list(HttpServletRequest request, HttpServletResponse response) {


        for (String s : value) {
            System.out.println(s);
        }
        return Collections.emptyList();
    }

}
