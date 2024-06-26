package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.entity.File;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.exception.DefaultImageLoadingException;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.FileRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FileService {
    private final List<String> allowedFileExtensions = Arrays.asList(
            "jpg", "jpeg", "png", "gif",
            "doc", "docx",
            "xls", "xlsx",
            "ppt", "pptx",
            "pdf",
            "txt",
            "odt", "ods", "odp"
    );
    private FileRepository fileRepository;
    private Mapper mapper;

    public FileDTO findFileById(FilePK id) {
        Optional<File> file = fileRepository.findById(id);
        return file.map(value -> mapper.fileToFileDTO(value)).orElse(new FileDTO());
    }

    public Collection<FileDTO> findByCriteria(List<SearchCriteria> searchCriteria) {
        return fileRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::fileToFileDTO)
                .toList();
    }

    public void uploadFile(FilePK filePK, MultipartFile multipartFile) throws IOException {
        File file = mapper.multipartFileToFile(multipartFile);
        String extension = StringUtils.getFilenameExtension(file.getName());
        if (!allowedFileExtensions.contains(extension)) {
            throw new IOException(String.format("Invalid file extension %s", extension));
        }
        file.setId(filePK);
        fileRepository.save(file);
    }

    public FileDTO updateFile(File file, MultipartFile multipartFile) throws IOException {
        file.setContent(mapper.multipartFileToFile(multipartFile).getContent());
        file = fileRepository.save(file);
        return mapper.fileToFileDTO(file);
    }

    @Transactional
    public void deleteFile(FilePK filePK) {
        fileRepository.deleteById(filePK);
    }

    @Transactional
    public void deleteFileById(FilePK id) throws FileNotFoundException {
        if (!fileRepository.existsById(id)) {
            throw new FileNotFoundException("Cannot delete file with an invalid ID");
        }

        fileRepository.deleteById(id);
    }

    public void storeImageFromUrl(FilePK filePK, String pictureUrl) throws IOException {
        URL url = new URL(pictureUrl);
        String filename = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);

        try (InputStream in = url.openStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            byte[] data = out.toByteArray();

            File file = File.builder()
                    .id(filePK)
                    .name(filename)
                    .content(data)
                    .build();
            fileRepository.save(file);
        }
    }

    private byte[] loadDefaultImage() {
        try {
            java.io.File file = new ClassPathResource("default.jpg").getFile();
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new DefaultImageLoadingException("Failed to load default image", e);
        }
    }

    public void storeImageFromUrlOrStoreDefault(FilePK filePK, String pictureUrl) {
        try {
            storeImageFromUrl(filePK, pictureUrl);
        } catch (IOException e) {
            File file = File.builder()
                    .id(filePK)
                    .name("default.jpg")
                    .content(loadDefaultImage())
                    .build();
            fileRepository.save(file);
        }
    }

    public FileDTO findFileByName(String name) throws FileNotFoundException {
        File file = fileRepository.findByName(name)
                .orElseThrow(() -> new FileNotFoundException("File not found with name: " + name));
        return mapper.fileToFileDTO(file);
    }
}
