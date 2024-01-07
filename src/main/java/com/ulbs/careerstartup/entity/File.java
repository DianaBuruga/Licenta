package com.ulbs.careerstartup.entity;

import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.entity.pk.FilePK;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class File {

    @EmbeddedId
    private FilePK id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FileType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) && Objects.equals(name, file.name) && Arrays.equals(content, file.content) && type == file.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, type);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
