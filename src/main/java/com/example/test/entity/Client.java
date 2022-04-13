package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CLIENT")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Integer id;

    @Column
    private String  firstName;
    @Column
    private String secondName;

    @OneToMany(mappedBy = "client", fetch=FetchType.EAGER)
    private List<AccountingRecords> accountingRecords = new ArrayList<>();


    public Client(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String toString() {
        return firstName + secondName;
    }

}
