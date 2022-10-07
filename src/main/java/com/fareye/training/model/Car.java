package com.fareye.training.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class Car {
    @NotNull @Min(1)
    private int id;
    @NotNull
    private String name;
    private String model;

}