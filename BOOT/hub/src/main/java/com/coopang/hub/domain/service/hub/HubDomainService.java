package com.coopang.hub.domain.service.hub;


import com.coopang.hub.application.request.hub.HubDto;
import com.coopang.hub.domain.entity.hub.HubEntity;
import com.coopang.hub.infrastructure.repository.hub.HubJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HubDomainService {
    private final HubJpaRepository hubJpaRepository;

    public HubDomainService(HubJpaRepository hubJpaRepository) {
        this.hubJpaRepository = hubJpaRepository;
    }

    public HubEntity createHub(HubDto hubDto) {
        HubEntity hubEntity = HubEntity.create(
                hubDto.getHubId()
                , hubDto.getHubName()
                , hubDto.getHubManagerId()
                , hubDto.getZipCode()
                , hubDto.getAddress1()
                , hubDto.getAddress2()
        );
        return hubJpaRepository.save(hubEntity);
    }
}