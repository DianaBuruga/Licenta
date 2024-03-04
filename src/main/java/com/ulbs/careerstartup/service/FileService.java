package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.constant.FileType;
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

@AllArgsConstructor
@Service
public class FileService {
    private FileRepository fileRepository;
    private Mapper mapper;

    private final List<String> allowedFileExtensions = Arrays.asList(
            "jpg", "jpeg", "png", "gif",
            "doc", "docx",
            "xls", "xlsx",
            "ppt", "pptx",
            "pdf",
            "txt",
            "odt", "ods", "odp"
    );

    public FileDTO findFileById(FilePK id) throws FileNotFoundException {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found with ID: " + id));
        return mapper.fileToFileDTO(file);
    }

    public FileDTO findByIdOrNull(FilePK filePK) {
        FileDTO fileDTO;
        try {
            fileDTO = findFileById(filePK);
        } catch (FileNotFoundException e) {
            fileDTO = null;
        }
        return fileDTO;
    }

    public Collection<FileDTO> findFilesByCriteria(List<SearchCriteria> searchCriteria) {
        return fileRepository
                .findAll(new GenericSpecification<>(searchCriteria), PageRequest.of(0, 10))
                .map(mapper::fileToFileDTO)
                .toList();
    }

    public FileDTO uploadOrFindFile(MultipartFile multipartFile, FilePK filePK, FileType fileType) throws IOException {
        FileDTO fileDTO;
        if (multipartFile != null) {
            fileDTO = uploadFile(fileType, filePK, multipartFile);
        } else {
            fileDTO = findByIdOrNull(filePK);
        }
        return fileDTO;
    }

    public FileDTO uploadFile(FileType fileType, FilePK filePK, MultipartFile multipartFile) throws IOException {
        File file = mapper.multipartFileToFile(multipartFile);
        String extension = StringUtils.getFilenameExtension(file.getName());
        if (!allowedFileExtensions.contains(extension)) {
            throw new IOException(String.format("Invalid file extension %s", extension));
        }
        file.setType(fileType);
        file.setId(filePK);
        file = fileRepository.save(file);
        return mapper.fileToFileDTO(file);
    }

    public FileDTO updateFile(File file, MultipartFile multipartFile) throws IOException {
        file.setContent(mapper.multipartFileToFile(multipartFile).getContent());
        file = fileRepository.save(file);
        return mapper.fileToFileDTO(file);
    }

    @Transactional
    public void deleteFile(FileDTO fileDTO) {
        fileRepository.delete(mapper.fileDTOToFile(fileDTO));
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
                    .type(FileType.PROFILE_PHOTO)
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
                    .type(FileType.PROFILE_PHOTO)
                    .name("default.jpg")
                    .content(loadDefaultImage())
                    .build();
            fileRepository.save(file);
        }
    }
}
