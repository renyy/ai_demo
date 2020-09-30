package com.example.demo.controller;

import com.example.demo.entity.Works;
import com.example.demo.service.VisionService;
import com.example.demo.service.WorksService;
import com.example.demo.utils.Md5CaculateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/aidemo")
@Slf4j
public class WorksController {

    @Autowired
    private VisionService visionService;

    @Autowired
    private WorksService worksService;

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws Exception {
        //计算上传文件的md5值，作为文件名
        byte[] bytes = file.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        String md5Str = Md5CaculateUtil.getMD5(inputStream);
        inputStream.reset();
        inputStream.mark(0);

        String fileName = file.getOriginalFilename();
        String fType = fileName.substring(fileName.lastIndexOf("."));
        fileName = String.format("%s%s", md5Str, fType);
        visionService.saveAndRecognizeImage(fileName, inputStream,id);
        return fileName;
    }

    @GetMapping(value = "/getWorksList")
    public Object getWorksList()  {
        return worksService.list();
    }

    @PostMapping(value = "/save")
    public Object save(@RequestBody  Works work)  {
        return worksService.save(work);
    }






}
