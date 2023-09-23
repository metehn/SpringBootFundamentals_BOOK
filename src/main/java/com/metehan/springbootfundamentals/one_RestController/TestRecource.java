package com.metehan.springbootfundamentals.one_RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRecource {

    @GetMapping("/test/message")
    public String getMessage() {
        return "{ \"message\": \"Metehan Spring REST\" }";
    }

    @GetMapping("/test/bean")
    public Player getBean(){
        return new Player(602,"Metehan Ersoy",4.5);
    }

    @GetMapping("/test/ok")
    public ResponseEntity<String> getOK(){
        return ResponseEntity.ok("massage");
    }

}
