package com.codesquad.sidedish06.service;

import com.codesquad.sidedish06.dao.OverviewDao;
import com.codesquad.sidedish06.domain.dto.RequestOverviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.codesquad.sidedish06.utils.JsonUtils.listOverview;

@RequiredArgsConstructor
@Service
public class OverviewServiceImpl implements OverviewService {

    private final OverviewDao overviewDao;

    @Override
    public Object create(String menu) throws IOException, URISyntaxException {
        RequestOverviewDTO[] overviews = listOverview("/" + menu);
        for (RequestOverviewDTO overview : overviews) {
            overviewDao.insert(overview, menu);
        }
        return overviews;
    }
}
