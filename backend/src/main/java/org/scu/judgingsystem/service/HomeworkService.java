package org.scu.judgingsystem.service;

import org.scu.judgingsystem.pojo.Homework;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Transactional
public interface HomeworkService {

    /**
     * 新增题目
     * @param homework 题目
     * @return 操作成功/失败
     */
    public boolean add(Homework homework);

    /**
     * 删除题目
     * @param id 题目id
     * @return 操作成功/失败
     */
    public boolean delete(Long id) throws IOException;

    /**
     * 根据某语言某查询题目
     * @param id_homework 题目id
     * @return 题目
     */
    public Homework getById(Long id_homework);

    /**
     * 查询某语言全部题目
     * @param lang 语言名称
     * @return 题目列表
     */
    public List<Homework> getAll(String lang);

    /**
     *  上传题目文件
     * @param file 文件对象
     * @param uploadDir 上传目录
     * @return 文件路径
     */
    public String uploadFile(MultipartFile file, String uploadDir) throws IOException;

    /**
     * 删除文件
     * @param filename 文件名
     * @return 删除成功/失败
     */
    public boolean deleteFile(String filename) throws IOException;
}
