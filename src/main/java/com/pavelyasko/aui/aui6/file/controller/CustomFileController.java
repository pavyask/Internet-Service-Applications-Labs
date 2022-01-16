package com.pavelyasko.aui.aui6.file.controller;

import com.pavelyasko.aui.aui6.file.dto.UploadFileResponse;
import com.pavelyasko.aui.aui6.file.entity.CustomFile;
import com.pavelyasko.aui.aui6.file.service.CustomFileService;
import com.pavelyasko.aui.aui6.file.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RestController
public class CustomFileController {

    private static final Logger logger = LoggerFactory.getLogger(CustomFileController.class);

    private FileStorageService fileStorageService;
    private CustomFileService customFileService;

    @Autowired
    public CustomFileController(FileStorageService fileStorageService, CustomFileService customFileService) {
        this.fileStorageService = fileStorageService;
        this.customFileService = customFileService;
    }

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("author") String fileAuthor,
                                         @RequestParam("description") String fileDescription,
                                         @RequestParam("file") MultipartFile file) {

        // UPLOADING FILE TO THE SERVER
        String fileName = fileStorageService.storeFile(file);

        // CREATING RECORD IN DB
//        CustomFile customFile = UploadFileRequest.dtoToEntityMapper().apply(fileInfoReq);
//        customFileService.create(customFile);
        customFileService.create(fileAuthor, fileDescription, fileName);

        // BUILDING RESPONSE
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable long id, HttpServletRequest request) {
        Optional<CustomFile> customFileOpt = customFileService.find(id);
        if (customFileOpt.isPresent()) {

            // Load file as Resource
            Resource resource = fileStorageService.loadFileAsResource(customFileOpt.get().getFileName());

            // Try to determine file's content type
            String contentType = null;
            try {
                contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            } catch (IOException ex) {
                logger.info("Could not determine file type.");
            }

            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }

        return ResponseEntity
                .notFound()
                .build();
    }
}