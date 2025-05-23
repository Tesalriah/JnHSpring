package kr.co.jnh.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileMultiSaveUtil {
    public static String uploadImg(MultipartFile file, HttpServletRequest request, String path, String id) throws Exception{
        /*
        // 로컬 서버 설정
        String[] savePathArr = {request.getServletContext().getRealPath("resources/img/upload/" + path + "/"), request.getServletContext().getRealPath("")};

        // target경로에서 프로젝트 경로로 이동
        int targetIndex = savePathArr[1].indexOf("\\target");
        savePathArr[1] = savePathArr[1].substring(0, targetIndex);
        savePathArr[1] += "\\src\\main\\webapp\\resources\\img\\upload\\" + path + "\\";

         */
        // 외부 경로 (톰캣 밖)
        String baseUploadPath = "/home/ubuntu/upload/";

        // 저장 경로 설정: /home/ubuntu/upload/{path}/{id}/
        String savePath = baseUploadPath + path + "/" + id + "/";
        Path directory = Paths.get(savePath);
        Files.createDirectories(directory);

        // 파일이름 설정 (파일형식 확장자 유지)
        String originalFileName = file.getOriginalFilename();
        String[] fileNameArr = originalFileName.split("\\.");
        // 중복방지 + 캐싱 이미지 로드를 방지하기 위해 고유값 설정
        fileNameArr[0] = id + "-" + System.currentTimeMillis();
        String fileName = fileNameArr[0] + "." + fileNameArr[1];

        /*for (String savePath : savePathArr) {
            // 이미지를 폴더에 저장하기 위해 경로 위에 폴더 생성
            savePath += id + "/";
            Path directory = Paths.get(savePath);
            Files.createDirectories(directory);
        */
            // 이미지 업로드
            File newFile = new File(savePath + fileName);
            file.transferTo(newFile);
        //}
        return fileName;
    }

    public static String getProjectRoot() {
        return System.getProperty("project.root", System.getProperty("user.dir"));
    }
}
