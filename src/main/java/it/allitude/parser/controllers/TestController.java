package it.allitude.parser.controllers;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Api("Rappresenta le queries/actions dell'entity Test")
public class TestController {

    @GetMapping(value = "/method")
    public String method(@RequestParam(name = "filter") String filter) {
        System.out.println(filter);
        return "xxxx";
    }

}
