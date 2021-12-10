package com.pavelyasko.aui.lab1.director.event.dto;

import com.pavelyasko.aui.lab1.director.entity.Director;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateDirectorEventRequest {

    private Long id;

    public static Function<Director, CreateDirectorEventRequest> entityToDtoMapper() {
        return entity -> CreateDirectorEventRequest.builder()
                .id(entity.getId())
                .build();
    }
}
