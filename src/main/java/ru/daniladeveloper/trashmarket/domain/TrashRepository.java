package ru.daniladeveloper.trashmarket.domain;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.daniladeveloper.trashmarket.infrustructure.entity.TrashEntity;

@Repository
public interface TrashRepository extends JpaRepository<TrashEntity, Long> {


}
