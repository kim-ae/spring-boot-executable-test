package br.com.kimae.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Value("${var.test}")
    private String varTest;
    
    @RequestMapping("/")
    public String index(){
        return varTest;
    }
    
    @RequestMapping("/props")
    public String props(){
        return new Properties().getString("prop.test");
    }
}
