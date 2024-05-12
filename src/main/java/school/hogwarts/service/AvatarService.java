package school.hogwarts.service;

import org.springframework.web.multipart.MultipartFile;
import school.hogwarts.model.Avatar;

import java.io.IOException;

public interface AvatarService {

    void uploadAvatar(Long studentId, MultipartFile avatar) throws IOException;

    Avatar findAvatar(Long studentId);

}
