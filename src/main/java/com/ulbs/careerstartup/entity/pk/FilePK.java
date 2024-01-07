package com.ulbs.careerstartup.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class FilePK implements Serializable {
    @Column(name = "table_id")
    private UUID tableId;
    @Column(name = "table_name")
    private String tableName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilePK that = (FilePK) o;
        return Objects.equals(tableId, that.tableId) && Objects.equals(tableName, that.tableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, tableName);
    }
}
