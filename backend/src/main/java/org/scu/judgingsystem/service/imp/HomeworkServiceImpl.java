package org.scu.judgingsystem.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.scu.judgingsystem.dao.HomeworkDao;
import org.scu.judgingsystem.pojo.Homework;
import org.scu.judgingsystem.service.HomeworkService;
import org.scu.judgingsystem.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.scu.judgingsystem.utils.FileUtils.unzip;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkDao homeworkDao;
    @Value("${file.homework-dir}")
    String directory;

    @Override
    public boolean add(Homework homework){
        if (homework != null) {
            homeworkDao.insert(homework);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) throws IOException {
        Homework homework = getById(id);
        // 删除文件
        // FileUtils.deleteFile(new File(homework.getSolutions()));
        // FileUtils.deleteFile(new File(homework.getUsecase()));
        // 删除数据库记录
        homeworkDao.deleteById(id);
        return true;
    }

    @Override
    public Homework getById(Long id_homework) {
        return homeworkDao.selectById(id_homework);
    }

    @Override
    public List<Homework> getAll(String lang) {
        QueryWrapper<Homework> qw = new QueryWrapper<>();
        qw.eq("language", lang);
        return homeworkDao.selectList(qw);
    }

    @Override
    public String uploadFile(MultipartFile file, String uploadDir) throws IOException {
        String dir = directory + uploadDir;

        if (file.getOriginalFilename().endsWith(".zip")) {
            dir += "/usecases/";;
            Path uploadPath = Paths.get(dir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            File temp = File.createTempFile("temp", ".zip");
            file.transferTo(temp);
            unzip(temp, dir);
            // 删除临时文件
            temp.delete();

            return Paths.get(dir).toString();
        } else {
            Path uploadPath = Paths.get(dir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath);

            return filePath.toString();
        }
    }

    @Override
    public boolean deleteFile(String filename) throws IOException {
        return FileUtils.deleteFile(new File(filename));
    }
}
