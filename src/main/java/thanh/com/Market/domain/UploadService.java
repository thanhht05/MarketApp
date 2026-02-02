package thanh.com.Market.domain;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class UploadService {

    @Autowired
    private Cloudinary cloudinary;

    public String handleUpLoadFile(MultipartFile file, String folder) throws IOException {

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", folder));

        // URL HTTPS
        return uploadResult.get("secure_url").toString();
    }
}
