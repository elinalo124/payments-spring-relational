
package com.payments.relational.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDTO {
    private String name;
    private String cuit;
    private String address;
    private String telephone;

    private Set<Long> customerIds;
    private Set<Long> cardIds;
    private Set<Long> promotionIds;
}
