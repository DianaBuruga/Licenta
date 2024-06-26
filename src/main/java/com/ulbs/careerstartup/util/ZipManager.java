package com.ulbs.careerstartup.util;

import com.ulbs.careerstartup.dto.FileDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

@Component
@NoArgsConstructor
public class ZipManager {

    private final String ZIP_NAME = "applicants.zip";

    public FileDTO createZipFromFiles(List<FileDTO> documents) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int index;
        boolean repeat;
        try (ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream)) {

            for (FileDTO document : documents) {
                index = 0;
                do {
                    try {
                        ZipEntry zipEntry = new ZipEntry(document.getName());
                        zos.putNextEntry(zipEntry);
                        repeat = false;
                    } catch (ZipException e) {
                        repeat = true;
                        index++;
                        document.setName(document.getName().replace(".pdf", index + ".pdf"));
                    }
                } while (repeat);

                ByteArrayInputStream bais = new ByteArrayInputStream(document.getContent());
                byte[] buffer = new byte[1024];
                int length;
                while ((length = bais.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
            }
        }
        return FileDTO.builder().name(ZIP_NAME).content(byteArrayOutputStream.toByteArray()).build();
    }
}
