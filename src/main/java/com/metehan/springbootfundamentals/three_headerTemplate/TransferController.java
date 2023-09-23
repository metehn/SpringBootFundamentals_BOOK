package com.metehan.springbootfundamentals.three_headerTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/***
 Bu bölümde REST tekniğiyle uzaktaki kaynakla iletişim kuran
 RestTemplate sınıfının kullanımında header ve cookie gönderilmesi ve alınması
 açıklanmaktadır.

 */

@Controller
public class TransferController {

    @GetMapping("/client/header")
    @ResponseBody
    public String getHeader() {

        String url = "http://localhost:8080/transfer/header";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add( "input", "Metehan" );

        HttpEntity<String> entity = new HttpEntity<String>("Gövde", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class);

        return "Başlık gönderildi: " + response.getBody();
    }

    @GetMapping("/client/cookie")
    @ResponseBody
    public String getCookie() {

        String url = "http://localhost:8080/transfer/cookie";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE,"input=Metehan");

        HttpEntity<String> entity = new HttpEntity<String>("Gövde", headers);
        ResponseEntity<String> response = restTemplate.exchange( url, HttpMethod.GET, entity, String.class);

        return "Çerez gönderildi: " + response.getBody();
    }

    @GetMapping("/client/setheader")
    @ResponseBody
    public String setHeader() {

        String url = "http://localhost:8080/transfer/setheader";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, String.class);
        String input = response.getHeaders().getFirst("input");

        return "Başlık alındı: " + response.getBody() + " başlık: " + input;
    }

    @GetMapping("/client/setcookie")
    @ResponseBody
    public String setCookie() {

        String url = "http://localhost:8080/transfer/setcookie";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY,String.class);
        String input = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        return "Çerez alındı: " + response.getBody() + " çerez: " + input;
    }
}

