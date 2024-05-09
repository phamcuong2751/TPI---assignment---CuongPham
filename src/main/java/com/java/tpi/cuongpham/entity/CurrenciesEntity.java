package com.java.tpi.cuongpham.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity()
@Table(name = "currencies")
@Getter()
@Setter()
@AllArgsConstructor()
@NoArgsConstructor()
public class CurrenciesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ccyCode;
    private String ccyName;
    private String ccySymbol;
    private String rate;
    private float rateFloat;
    private String chartName;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
