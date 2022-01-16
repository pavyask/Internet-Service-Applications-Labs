//package com.pavelyasko.aui.aui6.configuration;
//
//import com.pavelyasko.aui.aui6.file.entity.CustomFile;
//import com.pavelyasko.aui.aui6.file.service.CustomFileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class InitializedData {
//
//    private CustomFileService fileService;
//
//    @Autowired
//    public InitializedData(CustomFileService fileService) {
//        this.fileService = fileService;
//    }
//
//    @PostConstruct
//    private synchronized void init(){
//        CustomFile file1 = CustomFile.builder()
//                .author("author1")
//                .description("description1")
//                .filePathStr("file1.txt")
//                .build();
//        CustomFile file2 = CustomFile.builder()
//                .author("author2")
//                .description("description2")
//                .filename("file2.txt")
//                .build();
//        CustomFile file3 = CustomFile.builder()
//                .author("author3")
//                .description("description3")
//                .filename("file3.txt")
//                .build();
//
//        fileService.create(file1);
//        fileService.create(file2);
//        fileService.create(file3);
//    }
//}
