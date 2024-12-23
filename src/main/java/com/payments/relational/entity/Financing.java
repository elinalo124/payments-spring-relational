package com.payments.relational.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Financing extends Promotion{
    @Column(name = "number_of_quotas")
    private int numberOfQuotas;

    @Column()
    private float interest;

}
