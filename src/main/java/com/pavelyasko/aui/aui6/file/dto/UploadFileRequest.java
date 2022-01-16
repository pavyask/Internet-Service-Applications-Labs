package com.pavelyasko.aui.aui6.file.dto;

import com.pavelyasko.aui.aui6.file.entity.CustomFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.function.Function;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileRequest {
    private String author;

    private String description;

    private String filename;

    public static Function<UploadFileRequest, CustomFile> dtoToEntityMapper() {
        return request -> CustomFile.builder()
                .author(request.getAuthor())
                .description(request.getDescription())
                .fileName(request.getFilename())
                .build();
    }
}
