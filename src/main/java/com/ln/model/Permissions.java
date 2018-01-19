package com.ln.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissions implements Serializable {
    private Long id;
    private Integer permit;
    private String function;
}
