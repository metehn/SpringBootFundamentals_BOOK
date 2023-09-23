package com.metehan.springbootfundamentals;

import com.metehan.springbootfundamentals.one_RestController.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;

@Controller()
public class TestController {

    @GetMapping("/test1")
    @ResponseBody
    public String getTest(){

        return "Test sayfası get mapping";
    }

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("message", "Merhaba, dünya!");
        return "helloView";
    }

    @GetMapping("/hello2")
    public ModelAndView sayHello2() {
        ModelAndView modelAndView = new ModelAndView("helloView");
        modelAndView.addObject("message", "Merhaba, dünya!");
        return modelAndView;
    }

    @GetMapping("/status/type/{id}")
    @ResponseBody
    public ResponseEntity<?> getType( @PathVariable("id") long playerId) {
        Player player = new Player(playerId, "Orhan Gencebay", 12.34);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(  new MediaType("application", "json", Charset.forName("UTF-8")))
                .body(player);
    }

    @GetMapping("/example")
    @ResponseBody
    public String exampleMethod(@RequestHeader("User-Agent") String userAgent) {
        return "User Agent: " + userAgent;
    }

    @GetMapping("/auth-info")
    @ResponseBody
    public String authInfo(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestHeader("X-Api-Key") String apiKey) {
        return "Authorization Header: " + authorizationHeader + "\n" +
                "X-Api-Key Header: " + apiKey;
    }

    @GetMapping("/getHeader")
    @ResponseBody
    public String getHeaderValue(@RequestHeader("User-Agent") String userAgent) {
        return "User Agent: " + userAgent;
    }

}
