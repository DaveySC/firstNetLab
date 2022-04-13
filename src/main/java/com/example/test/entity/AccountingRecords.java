package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AccountingRecords")
public class AccountingRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(name = "receiptDate")
    private Date receiptDate;

    @Column(name = "returnDate")
    private Date returnDate;


    @Override
    public String toString() {
        return id + '\n' +returnDate.toString() + '\n' + returnDate.toString();
    }
}
