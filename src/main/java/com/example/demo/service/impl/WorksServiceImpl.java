package com.example.demo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Works;
import com.example.demo.mapper.WorksMapper;
import com.example.demo.service.WorksService;
import org.springframework.stereotype.Service;

@Service
public class WorksServiceImpl extends ServiceImpl<WorksMapper, Works> implements WorksService {
}
