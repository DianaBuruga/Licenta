package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.INLINE_FILENAME;
import static com.ulbs.careerstartup.controller.UserController.detectMimeType;

@RestController
@AllArgsConstructor
@RequestMapping("/files")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
@Tag(name = "File", description = "The File API")
public class FileController {

    private FileService fileService;

    @PostMapping
    public void uploadFile(@RequestParam UUID id,
                           @RequestParam String table,
                           @RequestParam FileType fileType,
                           @RequestParam MultipartFile multipartFile) throws IOException {
        fileService.uploadFile(new FilePK(id, table, fileType), multipartFile);
    }

    @PostMapping("/assets")
    public void uploadAssets(@RequestParam MultipartFile multipartFile) throws IOException {
        fileService.uploadFile(new FilePK(UUID.randomUUID(), "assets", FileType.ASSETS), multipartFile);
    }

    @GetMapping(value = "/view/{id}/{table}/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> viewFileById(@PathVariable UUID id, @PathVariable String table, @PathVariable FileType type) {
        FilePK filePK = FilePK.builder().tableId(id).tableName(table).type(type).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        if(fileDTO == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().contentType(detectMimeType(fileDTO.getContent(), fileDTO.getName())).header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileDTO.getName()).body(new ByteArrayResource(fileDTO.getContent()));
    }

    @GetMapping(value = "/view/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> viewFileByName(@PathVariable String name) throws FileNotFoundException {
        FileDTO fileDTO = fileService.findFileByName(name);

        return ResponseEntity.ok().contentType(detectMimeType(fileDTO.getContent(), fileDTO.getName())).header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileDTO.getName()).body(new ByteArrayResource(fileDTO.getContent()));
    }

    @DeleteMapping(value = "/{table}/{type}")
    public void deleteFile(@PathVariable String table, @PathVariable FileType type)
    {
        fileService.deleteFile(new FilePK(UUID.randomUUID(), table, type));
    }
}
