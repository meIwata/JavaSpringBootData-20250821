package fcu.pbiecs.springdemo.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // 處理HTTP請求的控制器
public class HelloWorldController {

    @GetMapping("/hello") // 把程式運行起來輸入http://localhost:8080/hello就看得到
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable("name") String name){
        return "Hello, " + name + "!";
    }
}
