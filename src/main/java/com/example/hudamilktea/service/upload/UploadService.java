package com.example.hudamilktea.service.upload;

import com.example.hudamilktea.model.enums.FileType;
import com.example.hudamilktea.service.product.request.ProductRequest;
import com.example.hudamilktea.service.productImage.ProductImageSaveRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadService {
    String UPLOAD_DIR = "D:\\case_study\\milk_tea_project\\src\\main\\resources\\assets\\productImage\\";
    String SAVE_UPLOAD_DIR = "\\assets\\productImage\\";
    public List<ProductImageSaveRequest> addFileToRequest(MultipartFile[] multipartFiles){
        List<ProductImageSaveRequest> list = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            ProductImageSaveRequest media = new ProductImageSaveRequest();

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());

            media.setUrl(fileName);
        }
        return list;
    }
    public List<ProductImageSaveRequest> transferFiles(MultipartFile[] multipartFiles) throws IOException {
        List<ProductImageSaveRequest> media = new ArrayList<>();
        for (MultipartFile file : multipartFiles) {
            ProductImageSaveRequest mediaSave = new ProductImageSaveRequest();
            String fileName = file.getOriginalFilename();
            String filePath = UPLOAD_DIR + fileName;
            String savePath = SAVE_UPLOAD_DIR + fileName;
            String contentType = file.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                mediaSave.setFileType(FileType.IMAGE);
            } else  {
                mediaSave.setFileType(FileType.VIDEO);

            }
            mediaSave.setUrl(savePath);
            media.add(mediaSave);
            file.transferTo(new File(filePath));
        }
        return media;
    }
}
