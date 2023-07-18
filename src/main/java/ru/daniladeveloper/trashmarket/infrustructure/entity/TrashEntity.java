package ru.daniladeveloper.trashmarket.infrustructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jooq.impl.TableRecordImpl;
import ru.daniladeveloper.trashmarket.domain.Trash;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trash")
public class TrashEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String title;

    private BigDecimal price;

    private String description;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorEntity author;

    @OneToOne
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private PhotoEntity photo;


    public static TrashEntity from(Trash trash) {
        return TrashEntity.builder()
            .title(trash.getTitle())
            .description(trash.getDescription())
            .price(trash.getPrice())
            .build();
    }
}
