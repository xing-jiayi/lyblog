-- auto-generated definition
-- 创建用户
CREATE USER 'lyblog'@'%'
    IDENTIFIED WITH mysql_native_password BY 'yJszaKhYm6Hs6pEd';

-- auto-generated definition
-- 创建数据库
DROP SCHEMA IF EXISTS lyblog;
create schema lyblog collate utf8mb4_0900_ai_ci;

-- 授权
grant alter, alter routine, create, create routine, create temporary tables, create view, delete, drop, event, execute, index, insert, lock tables, references, select, show view, trigger, update on lyblog.* to lyblog;

USE lyblog;
/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80406
 Source Host           : localhost:3306
 Source Schema         : lyblog

 Target Server Type    : MySQL
 Target Server Version : 80406
 File Encoding         : 65001

 Date: 22/10/2025 14:52:39
*/


-- 建表
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
                              `article_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章id',
                              `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '文章标题',
                              `cover` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '文章封面',
                              `summary` varchar(160) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '文章摘要',
                              `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `publish_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
                              `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                              `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
                              `read_num` int NOT NULL DEFAULT 1 COMMENT '被阅读次数',
                              PRIMARY KEY (`article_id`) USING BTREE,
                              INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767670515543445505 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_article_category_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_category_rel`;
CREATE TABLE `t_article_category_rel`  (
                                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                                           `article_id` bigint NOT NULL COMMENT '文章id',
                                           `category_id` bigint NOT NULL COMMENT '分类id',
                                           `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
                                           PRIMARY KEY (`id`) USING BTREE,
                                           INDEX `idx_category_id`(`category_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767662208934088705 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章所属分类关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_article_content
-- ----------------------------
DROP TABLE IF EXISTS `t_article_content`;
CREATE TABLE `t_article_content`  (
                                      `content_id` bigint NOT NULL AUTO_INCREMENT COMMENT '文章内容id',
                                      `article_id` bigint NOT NULL COMMENT '文章id',
                                      `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '文章正文',
                                      `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
                                      PRIMARY KEY (`content_id`) USING BTREE,
                                      INDEX `idx_article_id`(`article_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767670515560222721 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章内容表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_article_tag_rel
-- ----------------------------
DROP TABLE IF EXISTS `t_article_tag_rel`;
CREATE TABLE `t_article_tag_rel`  (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                                      `article_id` bigint NOT NULL COMMENT '文章id',
                                      `tag_id` bigint NOT NULL COMMENT '标签id',
                                      `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '删除标志位：0：未删除 1：已删除',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_article_id`(`article_id` ASC) USING BTREE,
                                      INDEX `idx_tag_id`(`tag_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767662208976031745 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章对应标签关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_blog_settings
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_settings`;
CREATE TABLE `t_blog_settings`  (
                                    `setting_id` bigint NOT NULL AUTO_INCREMENT COMMENT '设置id',
                                    `user_id` bigint NOT NULL COMMENT '用户id',
                                    `logo` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '博客Logo',
                                    `blog_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '博客名称',
                                    `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '作者名',
                                    `introduction` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '介绍语',
                                    `avatar` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '作者头像',
                                    `github_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'GitHub 主页访问地址',
                                    `csdn_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'CSDN 主页访问地址',
                                    `gitee_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'Gitee 主页访问地址',
                                    `zhihu_homepage` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '知乎主页访问地址',
                                    `custom_url` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '自定义链接',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                                    `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                                    PRIMARY KEY (`setting_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767455705128112129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '博客设置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_blog_settings
-- ----------------------------
INSERT INTO `t_blog_settings` VALUES (767455705128112128, 1977941932197703684, 'https://minio.crushtj.top/lyy/bebb8a02ca6379aa64cbb60083378472331801b876d4320a265523affeba2d21.png', 'LyBlog', '刑加一', '', 'http://127.0.0.1:9000/lyy/ffd8883e8ba3561a101d2f9c767c8d36e9faef53cdd52215d0fe1add77577a88.jpg', 'https://www.github.com/xing-jiayi', '', '', '', 'https://lyblog.crushtj.top', '2025-10-19 02:34:59', '2025-10-20 10:13:47', 0);

-- ----------------------------
-- Table structure for t_category
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category`  (
                               `category_id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类id',
                               `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '分类名称',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                               `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                               PRIMARY KEY (`category_id`) USING BTREE,
                               UNIQUE INDEX `uk_name`(`name` ASC) USING BTREE,
                               INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767659302939922433 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
                          `tag_id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签id',
                          `tag_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '标签名称',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除标志位：0：未删除 1：已删除',
                          PRIMARY KEY (`tag_id`) USING BTREE,
                          UNIQUE INDEX `uk_name`(`tag_name` ASC) USING BTREE,
                          INDEX `idx_create_time`(`create_time` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 767659430312546305 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '文章标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
                           `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
                           `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                           `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                           `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除：0：未删除 1：已删除',
                           PRIMARY KEY (`user_id`) USING BTREE,
                           UNIQUE INDEX `uk_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1977941932197703685 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1977941932197703684, 'ayi', '$2a$10$lNsWzdlPHeWs6/S97Wtlx.fpc6fMOkj74wgcGz6GAR2r/qacgpl2O', '2025-10-14 14:51:03', '2025-10-14 09:12:07', 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
                                `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
                                `user_id` bigint NOT NULL COMMENT '用户id',
                                `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
                                `role_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`role_id`) USING BTREE,
                                INDEX `idx_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
