

create database ai_test;

GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON ai_test.* TO ai_test@root IDENTIFIED BY 'root';

-- ----------------------------
-- Table structure for work_test
-- ----------------------------
DROP TABLE IF EXISTS `work_test`;
CREATE TABLE `work_test` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `task_no` varchar(32) DEFAULT NULL COMMENT '任务编号',
  `task_name` varchar(64) DEFAULT NULL COMMENT '计划名称',
  `work_place` varchar(200) DEFAULT NULL COMMENT '任务地点(施工地址)',
  `task_content` varchar(1010) DEFAULT NULL COMMENT '任务内容(工作内容)',
  `start_time` datetime DEFAULT NULL COMMENT '任务开始时间（yyyy-MM-dd HH:mm:ss）',
  `end_time` datetime DEFAULT NULL COMMENT '任务结束时间（yyyy-MM-dd HH:mm:ss）',
  `connected_people_nums` int(32) DEFAULT '0' COMMENT '工作人员数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='计划表';

-- ----------------------------
-- Records of work_test
-- ----------------------------
INSERT INTO `work_test` VALUES ('1', '20200929001', '施工作业A', '北京市丰台区丰台科技园', '检修', '2020-09-29 15:10:23', '2020-09-29 18:10:25', '0');
INSERT INTO `work_test` VALUES ('2', '任务编号', '计划名称', '任务地点', '任务内容', '2020-09-29 00:00:00', '2020-09-30 00:00:00', '0');
INSERT INTO `work_test` VALUES ('3', '任务编号', '计划名称', '任务地点', '任务内容', '2020-09-29 00:00:00', '2020-09-30 00:00:00', '0');
INSERT INTO `work_test` VALUES ('4', '钱钱钱', '啊哇哇哇', '顶顶顶顶', '呃呃呃', '2020-09-10 00:00:00', '2020-09-24 00:00:00', '0');
