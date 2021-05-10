package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.spi.XmlReader;

@RestController
@RequestMapping("/api")
public class PostController {

    // POST 사용예시
    // HTML <Form> 사용할 때
    // Ajax에서의 비동기 처리 (ex> 검색)

    // json, XML, multipart-form, text-plain 형태등 여러가지 타입으로
    // 데이터를 올릴 수 있다. 이떄 produces 를 사용하여 지원할수있는 형태들을 작성해주면 된다.
    // 하지만 우리는 JSON만 사용할거라서 별도로 작성해주지 않아도 된다.
    // ex > @PostMapping(value = "/postMethod", produces = {XmlReader})
    @PostMapping(value = "/postMethod")


    // HTTP 통신시 POST의 body에다가 data를 넣어서 보내겠다는 의미가 되므로
    // RequestBody로 받겠다고 해주어야 한다 ! >> @RequestBody
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

//    @PutMapping("/putMethod")
//    public void put() {
//
//    }
//
//    @PatchMapping("/patchMethod")
//    public void patch() {
//
//    }
}
