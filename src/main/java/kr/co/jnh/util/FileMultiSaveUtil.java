package kr.co.jnh.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMultiSaveUtil {
    public static String uploadImg(MultipartFile file, HttpServletRequest request, String path, String id) throws Exception{
        String[] savePathArr = {request.getServletContext().getRealPath("resources/img/upload/" + path + "/"), request.getServletContext().getRealPath("webapp/resources/img/upload/" + path + "/")};

        // 파일이름 설정 (파일형식 확장자 유지)
        String originalFileName = file.getOriginalFilename();
        String[] fileNameArr = originalFileName.split("\\.");
        fileNameArr[0] = id + "";
        String fileName = fileNameArr[0] + "." + fileNameArr[1];

        for (String savePath : savePathArr) {
            // 이미지를 폴더에 저장하기 위해 경로 위에 폴더 생성
            savePath += id + "/";
            Path directory = Paths.get(savePath);
            Files.createDirectories(directory);

            // 이미지 업로드
            File newFile = new File(savePath + fileName);
            file.transferTo(newFile);
        }
        return fileName;
    }

}
