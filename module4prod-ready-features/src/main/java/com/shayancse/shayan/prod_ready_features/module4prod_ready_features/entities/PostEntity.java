package com.shayancse.shayan.prod_ready_features.module4prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Audited //It will Audit all the fields defined in this class
public class PostEntity extends AuditableEntity {

    //By using Hibernate-envers, it will create extra table like posts_aud to keep track of all the Audits made

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    @NotAudited //Now the description field will not be Audited
    private String description;

    @PrePersist
    void beforeSave() {}

    @PreUpdate
    void beforeUpdate() {}

    @PreRemove
    void beforeDelete() {}

}
