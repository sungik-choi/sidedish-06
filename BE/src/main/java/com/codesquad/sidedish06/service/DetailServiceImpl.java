package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.DetailDao;
import com.codesquad.sidedish06.domain.dto.RequestDetailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

import static com.codesquad.sidedish06.utils.JsonUtils.listDetail;

@RequiredArgsConstructor
@Service
public class DetailServiceImpl implements DetailService {

    private final DetailDao detailDao;

    @Override
    public Object create() throws URISyntaxException, JsonProcessingException {
        RequestDetailDTO[] details = listDetail();
        for (RequestDetailDTO detail : details) {
            detailDao.create(detail);
        }
        return details;
    }
}
