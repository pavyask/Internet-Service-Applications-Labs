package com.pavelyasko.aui.lab1.director.dto;

import com.pavelyasko.aui.lab1.director.entity.Director;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetDirectorResponse {
    private Long id;

    private String name;

    private String surname;

    private LocalDate dateOfBirth;

    public static Function<Director, GetDirectorResponse> entityToDtoMapper() {
        return director -> GetDirectorResponse.builder()
                .id(director.getId())
                .name(director.getName())
                .surname(director.getSurname())
                .dateOfBirth(director.getDateOfBirth())
                .build();
    }
}
