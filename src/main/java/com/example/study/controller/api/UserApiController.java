package com.example.study.controller.api;

import com.example.study.ifc.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j // simple logging facade for java : 어떤 한 파일에 log를 남길 수 있다.
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        return userApiLogicService.orderInfo(id);
    }

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable) {
        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }
}
