package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest() {
        return "Hi getMethod";
    }

    // localhost:8080/api/getParameter?id=1234&password=abcd
    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {

        // 지역내변수에서 미리 password를 사용할경우 (추천 x)
        String password = "bbbb";
        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id + pwd;
    }

    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // HTTP 통신 규격에서 JSON을 표준처럼 사용하기 떄문에
        // Springboot에서는 기본적으로 JSON 라이브러리를 내장하고있다.
        // 따로뭔가를 설정하지 않는이상 객체를 리턴한다는건 JSON으로 바꾼다는 뜻이된다.
        // {"account" : "", "email" : "", "page" : 0}
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
