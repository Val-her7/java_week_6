package dev.val.COGIP_API.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int companyId;

    @Column(nullable = false)
    private String name;

    @Column(name = "vat_number", unique = true, nullable = false)
    private String vatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type", nullable = false)
    private CompanyType companyType;

    @OneToMany(mappedBy = "company")
    private List<Contact> contacts;

    @OneToMany(mappedBy = "company")
    private List<Invoice> invoices;

}
