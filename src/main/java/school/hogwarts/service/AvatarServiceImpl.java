package school.hogwarts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school.hogwarts.exception.EntityNotFoundException;
import school.hogwarts.model.Avatar;
import school.hogwarts.model.Student;
import school.hogwarts.repository.AvatarRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
public class AvatarServiceImpl implements AvatarService {

    private final StudentService studentService;

    private final AvatarRepository avatarRepository;

    @Value("${avatars.dir.path}")
    private String avatarsDir;


    public AvatarServiceImpl(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }



    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentService.get(studentId);
        Path filePath = buildFilePath(student, avatarFile.getOriginalFilename());

        saveInDirectory(filePath, avatarFile);
        saveInDb(studentId, student, filePath, avatarFile);



    }

    @Override
    public Avatar findAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElseThrow(EntityNotFoundException::new);
    }

    private Avatar findOrCreateAvatar(Long studentId) {
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private Path buildFilePath(Student student, String fileName) {
        return Path.of(avatarsDir,student.getId() + "_" + student.getName() + "_" + getExtensions(fileName));
    }

    private void saveInDirectory(Path filePath, MultipartFile avatarFile) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();

                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
    }

    private void saveInDb(Long studentId, Student student, Path filePath, MultipartFile avatarFile) throws IOException {
        Avatar avatar = findOrCreateAvatar(studentId);

        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());

        avatarRepository.save(avatar);

    }
}
