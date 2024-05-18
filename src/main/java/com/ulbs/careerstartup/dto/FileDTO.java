package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.FileType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FileDTO {

    @NotNull
    private UUID tableId;

    @NotNull(message = "Table name is required")
    @NotEmpty(message = "Table name is required")
    private String tableName;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Content is required")
    @NotEmpty(message = "Content is required")
    private byte[] content;

    @NotNull(message = "Type is required")
    @NotEmpty(message = "Type is required")
    private FileType type;
}
