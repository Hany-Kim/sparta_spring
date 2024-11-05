package com.sparta.spring_mvc.response;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/response")
public class ResponseController {
    // [ResponseHeader]
    // Content-Type: text/html
    // [ResponseBody]
    // {"name":"Hany","age":29}
    @GetMapping("/json/string")
    @ResponseBody
    public String helloStringJson(){
        return "{\"name\":\"Hany\",\"age\":29}";
    }

    // [ResponseHeader]
    // Content-Type: application/json
    // [ResponseBody]
    // {"name":"Hany","age":29}
    // JAVA의 객체는 클라이언트에서 이해할 수 없다.
    // 스프링 내부에서 JAVA의 객체를 JSON형태로 반환해준다.
    @GetMapping("/json/class")
    @ResponseBody
    public Star helloClassJson(){
        return new Star("Hany", 29);
    }
}
