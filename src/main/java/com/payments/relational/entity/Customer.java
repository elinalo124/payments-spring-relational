package com.payments.relational.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complete_name")
    @NonNull
    private String completeName;

    @Column(name = "dni")
    @NonNull
    private String dni;

    @Column(nullable=false, name = "cuil")
    @NonNull
    private String cuil;

    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "telephone")
    @NonNull
    private String telephone;

    @Column(name = "entry_date")
    @NonNull
    private LocalDate entryDate;

    @ManyToMany(mappedBy = "members")
    @JsonBackReference
    private Set<Bank> banks;
}
