package com.example.study.model.network.response;

import com.example.study.model.entity.Partner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryApiResponse {
    private Long id;

    private String type;

    private String title;
}
