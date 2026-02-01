package thanh.com.Market.domain;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    private final String UPLOAD_DIR = "D:/marketUpload/image/";

    public String handleUpLoadFile(MultipartFile file, String targetFolder) throws IOException {
        if (file.isEmpty())
            return "";

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        File uploadDir = new File(UPLOAD_DIR);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File destination = new File(uploadDir, fileName);
        file.transferTo(destination);

        return "images/" + fileName;
    }

}