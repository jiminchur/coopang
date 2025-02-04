package com.coopang.hub.domain.repository.shipper;

import com.coopang.hub.application.request.shipper.ShipperSearchCondition;
import com.coopang.hub.domain.entity.shipper.ShipperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShipperRepository {
    Optional<ShipperEntity> findByShipperId(UUID shipperId);

    Optional<ShipperEntity> findByShipperIdAndIsDeletedFalse(UUID shipperId);

    Page<ShipperEntity> findAll(Pageable pageable);

    List<ShipperEntity> findShipperList(ShipperSearchCondition condition);

    Page<ShipperEntity> search(ShipperSearchCondition condition, Pageable pageable);
}
