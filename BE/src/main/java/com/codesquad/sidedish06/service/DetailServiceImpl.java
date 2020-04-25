package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.DetailDao;
import com.codesquad.sidedish06.domain.dto.RequestDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.codesquad.sidedish06.utils.JsonUtils.listDetail;

@RequiredArgsConstructor
@Service
public class DetailServiceImpl implements DetailService {

    private final DetailDao detailDao;

    @Override
    public Object save() throws URISyntaxException, JsonProcessingException {
        RequestDetail[] details = listDetail();
        for (RequestDetail detail : details) {
            if(detail!=null) {
                validate(detail);
                detailDao.insert(detail);
            }
        }
        return details;
    }

    private void validate(RequestDetail detail) {
        if(detail.getThumb_images()==null) {
            detail.setThumb_images(new ArrayList<>());
        }

        if(detail.getDetail_section()==null) {
            detail.setDetail_section(new ArrayList<>());
        }
    }
}
