package com.oven.server;

import com.oven.server.api.work.domain.Genre;
import com.oven.server.api.work.domain.Provider;
import com.oven.server.api.work.domain.Work;
import com.oven.server.api.work.domain.WorkProvider;
import com.oven.server.api.work.repository.ProviderRepository;
import com.oven.server.api.work.repository.WorkProviderRepository;
import com.oven.server.api.work.repository.WorkRepository;
import com.oven.server.common.exception.BaseException;
import com.oven.server.common.response.ResponseCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class SaveWorkTest {

    @Autowired
    private WorkRepository workRepository;
    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private WorkProviderRepository workProviderRepository;


    @Test
    @Transactional
    public void saveWork() {

        Genre genre = new Genre();
        genre.setAction(true);
        genre.setComedy(true);

        Provider provider = providerRepository.findById(1L).orElseThrow(
                () -> new BaseException(ResponseCode.PROVIDER_NOT_FOUND)
        );

        Work work = Work.builder()
                .titleKr("하이")
                .titleEng("Hi")
                .year(2023)
                .ageRating("12세이상 관람가")
                .poster("hi.html")
                .summary("hihihihihibye")
                .director("김현수, 임채리, 박시영")
                .actor("김현수, 임채리, 박시영")
                .genre(genre)
                .build();

        workRepository.saveAndFlush(work);

        WorkProvider workProvider = WorkProvider.builder()
                .provider(provider)
                .work(work)
                .build();

        workProviderRepository.saveAndFlush(workProvider);
    }

}
