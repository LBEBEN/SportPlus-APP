package pl.lukasbeben;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @RequestMapping("/helloPanel")
    @ResponseBody
    public String hello(){
        return "Witaj w aplikacji SPORT PLUS";
    }
}
