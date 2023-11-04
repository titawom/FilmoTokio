package com.example.FilmoTokio.DTO;

import com.example.FilmoTokio.entity.enums.TypePersonEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String name;
    private String surname;
    private TypePersonEnum type;
}

