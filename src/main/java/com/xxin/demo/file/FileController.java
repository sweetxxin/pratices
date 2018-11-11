package com.xxin.demo.file;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/index")
    public String index(){
        return "/file/file";
    }
    @RequestMapping("/originUpload")
    @ResponseBody
    public void originUpload(@RequestParam("upload")MultipartFile file) throws IOException {
        FileOutputStream out = new FileOutputStream("E:\\Intelij_work\\pratices\\src\\main\\resources\\static\\file\\tmp\\t1");
        IOUtils.copy(file.getInputStream(),out );

    }

}
