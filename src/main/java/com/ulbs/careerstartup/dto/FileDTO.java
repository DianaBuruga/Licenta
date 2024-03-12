package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.FileType;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private String tableName;

    @NotNull
    private String name;

    @NotNull
    private byte[] content;

    @NotNull
    private FileType type;
}
