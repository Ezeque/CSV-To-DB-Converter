package teste.pagamento.rest.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class BatchService {
    @Autowired
    JobLauncher jobLauncher;
    @Value("${filesPath}")
    String filesPath;
    String fileName;
    @Autowired
    Job saveDataJob;

    public void startJob(MultipartFile file) throws Exception{
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        saveFile(file, fileName);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("fileName", fileName)
                .toJobParameters();
        jobLauncher.run(saveDataJob, jobParameters);
    }

    private void saveFile(MultipartFile file, String fileName) throws IOException {
        try{
            Path fileNewPath = Path.of(filesPath, fileName);
            Files.copy(file.getInputStream(), fileNewPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(fileNewPath);
        }
        catch (Exception e){
            throw e;
        }

    }
}
