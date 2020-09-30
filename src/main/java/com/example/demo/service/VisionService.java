package com.example.demo.service;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.facebody.model.v20191230.DetectBodyCountRequest;
import com.aliyuncs.facebody.model.v20191230.DetectBodyCountResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.example.demo.entity.Works;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;


@Service
@Slf4j
public class VisionService {
    @Value("${aliyun.accessKeyId}")
    private String accessKey;

    @Value("${aliyun.accessSecret}")
    private String accessSecret;

    @Autowired
    private WorksService worksService;


    /**
     * 上传文件到OSS
     * @return String oss路径
     */
    public String uploadFile2OSS(String filename,InputStream inputStream) {
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest("renyiyong-bj",filename,inputStream);
        PutObjectResult putObjectResult =  ossClient.putObject(putObjectRequest);

        Date expiration = new Date(System.currentTimeMillis()+ 3600 * 1000);
        URL url = ossClient.generatePresignedUrl("renyiyong-bj", filename, expiration);
        return  url.toString();

    }


    public String recognizeBodyCount(String filename, InputStream inputStream) throws Exception {
        String url =  uploadFile2OSS(filename,inputStream);
        String url2= url.substring(0, url.indexOf("?"));

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        DetectBodyCountRequest request = new DetectBodyCountRequest();
        request.setImageURL(url2);
        request.setRegionId("cn-shanghai");

        String count = "";
        try {
            DetectBodyCountResponse response = client.getAcsResponse(request);
            count = response.getData().getPersonNumber()+"";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }


    public void saveAndRecognizeImage(String filename, InputStream inputStream,Integer id) {
        try {
            //识别人数
            inputStream.reset();
            inputStream.mark(0);
            String number = this.recognizeBodyCount(filename , inputStream);

            Works work = new Works();
            work.setId(id).setConnectedPeopleNums(number);
            worksService.updateById(work);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }





}
