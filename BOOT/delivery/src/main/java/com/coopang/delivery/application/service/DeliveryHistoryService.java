package com.coopang.delivery.application.service;

import com.coopang.delivery.application.response.delivery.DeliveryResponseDto;
import com.coopang.delivery.application.response.deliveryhistory.DeliveryHistoryResponseDto;
import com.coopang.delivery.application.service.deliveryhubhistory.DeliveryHubHistoryService;
import com.coopang.delivery.application.service.deliveryuserhistory.DeliveryUserHistoryService;
import com.coopang.delivery.domain.entity.deliveryhubhistory.DeliveryHubHistoryEntity;
import com.coopang.delivery.domain.entity.deliveryuserhistory.DeliveryUserHistoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j(topic = "DeliveryHistoryService")
@Service
@Transactional
public class DeliveryHistoryService {

    private final DeliveryHubHistoryService deliveryHubHistoryService;
    private final DeliveryUserHistoryService deliveryUserHistoryService;

    public DeliveryHistoryService(DeliveryHubHistoryService deliveryHubHistoryService, DeliveryUserHistoryService deliveryUserHistoryService) {
        this.deliveryHubHistoryService = deliveryHubHistoryService;
        this.deliveryUserHistoryService = deliveryUserHistoryService;
    }


    public List<DeliveryHistoryResponseDto> findByDeliveryId(UUID deliveryId) {
        List<DeliveryUserHistoryEntity> userHistory = deliveryUserHistoryService.getHubHistoryById(deliveryId);
        List<DeliveryHubHistoryEntity> hubHistory = deliveryHubHistoryService.getHubHistoryById(deliveryId);

        // 두 리스트에서 DTO 생성 후 합침
        List<DeliveryHistoryResponseDto> userHistoryList = new java.util.ArrayList<>(userHistory.stream()
                .map(userDeliveryHistory -> new DeliveryHistoryResponseDto(
                        userDeliveryHistory.getDeliveryId(),
                        userDeliveryHistory.getDeliveryRouteHistoryStatus(),
                        userDeliveryHistory.getCreatedAt()
                ))
                .toList());

        List<DeliveryHistoryResponseDto> hubHistoryList = hubHistory.stream()
                .map(hubDeliveryHistory -> new DeliveryHistoryResponseDto(
                        hubDeliveryHistory.getDeliveryId(), // DeliveryEntity에서 ID 가져오기
                        hubDeliveryHistory.getDeliveryRouteHistoryStatus(), // DeliveryEntity의 상태 가져오기
                        hubDeliveryHistory.getCreatedAt() // DeliveryEntity의 생성 시간 가져오기
                ))
                .toList();

        // 두 리스트를 합쳐서 반환
        userHistoryList.addAll(hubHistoryList);
        return userHistoryList;
    }
}


