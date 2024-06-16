package org.scu.judgingsystem.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.scu.JudgeService;
import org.scu.black_box_result.BlackBoxResult;
import org.scu.judgingsystem.dao.RecordDao;
import org.scu.judgingsystem.pojo.Judgement;
import org.scu.judgingsystem.pojo.Ranking;
import org.scu.judgingsystem.pojo.Record;
import org.scu.judgingsystem.pojo.Submit;
import org.scu.judgingsystem.service.SubmitService;
import org.scu.judgingsystem.utils.DynamicServiceLoader;
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

@Service
@Slf4j
public class SubmitServiceImpl implements SubmitService {
    @Autowired
    private RecordDao recordDao;
    @Value("${file.user-dir}")
    private String directory;

    @Value("${file.plugin-dir}")
    private String PLUGIN_SOURCE;

    @Override
    public Long add(Record record, String filePath) {
        if (record != null) {
            // 新增记录并返回id
            DynamicServiceLoader dynamicServiceLoader = new DynamicServiceLoader();
            try {
                dynamicServiceLoader.loadNewService(PLUGIN_SOURCE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            JudgeService judgeService = dynamicServiceLoader.getJudgeService("java");

            BlackBoxResult blackBoxResult = judgeService.blackBoxTest(new File(filePath), record.getHomework());
            record.setBlack(blackBoxResult.getFailedUseCase());
            record.setScore(blackBoxResult.getScore());
            log.info("提交结果" + record);

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
