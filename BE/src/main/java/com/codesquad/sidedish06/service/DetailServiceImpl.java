package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.DetailDao;
import com.codesquad.sidedish06.domain.dto.RequestDetail;
import com.codesquad.sidedish06.domain.dto.ResponseDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.codesquad.sidedish06.utils.DaoUtils.HASHES;
import static com.codesquad.sidedish06.utils.JsonUtils.data;
import static com.codesquad.sidedish06.utils.UrlUtils.BASE_URL;

@RequiredArgsConstructor
@Service
public class DetailServiceImpl implements DetailService {

    private final DetailDao detailDao;

    @Override
    public void save() throws URISyntaxException, JsonProcessingException {
        RequestDetail[] details = listDetail();
        for (RequestDetail detail : details) {
            if (detail != null) {
                validate(detail);
                detailDao.insert(detail);
            }
        }
    }

    @Override
    public ResponseDetail read(String hash) {
        return detailDao.read(hash);
    }

    private void validate(RequestDetail detail) {
        if (detail.getThumb_images() == null) {
            detail.setThumb_images(new ArrayList<>());
        }

        if (detail.getDetail_section() == null) {
            detail.setDetail_section(new ArrayList<>());
        }
    }

    private RequestDetail[] listDetail() throws URISyntaxException, JsonProcessingException {
        RequestDetail[] details = new RequestDetail[HASHES.length];

        for (int i = 0; i < HASHES.length; i++) {
            String url = BASE_URL + "/detail/" + HASHES[i];
            String data = data(url);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            JsonNode jsonNode = objectMapper.readValue(data, JsonNode.class).get("data");

            details[i] = objectMapper.convertValue(jsonNode, RequestDetail.class);

            if (details[i] != null) {
                details[i].setHash(HASHES[i]);
            }
        }

        return details;
    }
}
