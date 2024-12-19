package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("FINANCING")
public class Financing extends Promotion{
    @Column(name = "number_of_quotas")
    private int numberOfQuotas;

    @Column()
    private float interest;
}
