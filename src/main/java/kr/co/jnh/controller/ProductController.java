package kr.co.jnh.controller;

import kr.co.jnh.domain.Product;
import kr.co.jnh.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("addProduct")
    public String getAddProduct(){return "product/addProduct";}

    @PostMapping("/addProduct")
    public String addProduct(Product product, HttpServletRequest request, @RequestParam("uploadFile")MultipartFile file){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String today = format.format(date);
        String price = null;
        System.out.println(request.getServletContext().getRealPath("/upload"));

        try {
            System.out.println(productService.productIdCheck(today));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            String savePath = "C:/Users/Tesalriah/IdeaProjects/JnHSpring/src/main/webapp/resources/img/upload/";
            System.out.println("savePath = " + savePath);

            String originalFileName = file.getOriginalFilename();

            File newFile = new File(savePath + originalFileName);

            file.transferTo(newFile);
            
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("price = " + price);
        System.out.println(today);
        System.out.println(product.toString());
//        String[] sizeArr = product.getSize().split(",");
//        String[] stockArr = product.getSize().split(",");
//
//        String fileName = null;
//        MultipartFile file = product.getUploadFile();
//        if(!file.isEmpty()){
//            String originalFileName = file.getOriginalFilename();
//            String ext = FilenameUtils.getExtension(originalFileName);
//            UUID uuid = UUID.randomUUID();
//            fileName = originalFileName;
//            try{
//                file.transferTo(new File("C:/upload/"+fileName));
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//        }
        return "redirect:/";
    }
}
