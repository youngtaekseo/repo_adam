package com.ucl.infra.member;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class MemberImageService {
	
	// 이미지 로드
    private final ResourceLoader resourceLoader;

    public MemberImageService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
	public String getBase64StaticImage(String path) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + path);
        BufferedImage bufferedImage = ImageIO.read(resource.getInputStream());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }
	
    public String getBase64ExternalImage(String path, String ext) throws IOException {
        // 이미지 파일을 파일 시스템에서 로드
        File imgPath = new File(path);
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // 이미지를 byte 배열로 변환
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, ext, outputStream); // 이미지 형식을 "png"로 설정
        byte[] imageBytes = outputStream.toByteArray();
        
        // byte 배열을 Base64 문자열로 인코딩하여 반환
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
