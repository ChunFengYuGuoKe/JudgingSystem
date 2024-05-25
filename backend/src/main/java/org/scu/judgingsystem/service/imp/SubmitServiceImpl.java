package org.scu.judgingsystem.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.scu.judgingsystem.dao.RecordDao;
import org.scu.judgingsystem.pojo.Judgement;
import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import org.scu.judgingsystem.pojo.Submit;
import org.scu.judgingsystem.service.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class SubmitServiceImpl implements SubmitService {
    @Autowired
    private RecordDao recordDao;
    @Value("${file.user-dir}")
    private String directory;

    @Override
    public Long add(Record record) {
        if (record != null) {
            // 新增记录并返回id
            recordDao.insert(record);
            return record.getId();
        }
        return 0L;
    }

    @Override
    public Judgement getOne(Long id_submit) {
        return recordDao.getOneById(id_submit);
    }

    @Override
    public List<Submit> getAll(String username, Long id_homework) {
        return recordDao.getAll(username, id_homework);
    }

    @Override
    public List<Ranking> getRanking(Long id_class) {
        return recordDao.getRanking(id_class);
    }

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        // 创建存储文件的目录
        Path uploadPath = Paths.get(directory + path);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 构建保存文件的路径
        Path filePath = uploadPath.resolve(file.getOriginalFilename());

        // 将上传的文件保存到目标路径
        Files.copy(file.getInputStream(), filePath);

        return String.valueOf(filePath);
    }
}
