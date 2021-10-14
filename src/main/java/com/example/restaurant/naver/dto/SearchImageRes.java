package com.example.restaurant.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;

    private List<SearchLocalRes.SearchLocalItem> items;


    // 배열 형태로 값이 들어가 있는 것을 편하게 받기 위해 static 으로 메모리에 먼저 띄워둔 상태로 시작한다.
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItem {
        private String title;
        private String link;
        private String thumbnail;
        private String telephone;
        private String sizeheight;
        private String sizewidth;

    }

}
