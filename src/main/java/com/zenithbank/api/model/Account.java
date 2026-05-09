package com.zenithbank.api.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a Banking Account stored in Mainframe DB2.
 * Note the use of @Table(schema = "BANK") as Mainframe DB2 heavily uses schemas/qualifiers.
 */
@Entity
@Table(name = "ACCOUNTS", schema = "BNKADM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @Column(name = "ACC_ID", length = 10)
    private String accountId;

    @Column(name = "CUST_NAME", nullable = false, length = 50)
    private String customerName;

    @Column(name = "ACC_TYPE", nullable = false, length = 10)
    private String accountType; // SAVINGS, CHECKING

    @Column(name = "BALANCE", precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "STATUS", length = 1)
    private String status; // A=Active, I=Inactive

    @Column(name = "LAST_UPD_TS")
    private LocalDateTime lastUpdatedTimestamp;
}
