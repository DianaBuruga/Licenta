package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.FileType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO {

    @NotNull
    private UUID tableId;

    @NotNull
    private String tableName;

    @NotNull
    private String name;

    @NotNull
    private byte[] content;

    @NotNull
    private FileType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileDTO fileDTO = (FileDTO) o;
        return Objects.equals(tableId, fileDTO.tableId) && Objects.equals(tableName, fileDTO.tableName) && Objects.equals(name, fileDTO.name) && Arrays.equals(content, fileDTO.content) && type == fileDTO.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tableId, tableName, name, type);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
