package com.midasdev.mochat.sample.controller;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "sample")
@Getter
@Builder
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    public static SampleEntity getSample() {
        return SampleEntity.builder().name("stark").age(30).build();
    }
}
