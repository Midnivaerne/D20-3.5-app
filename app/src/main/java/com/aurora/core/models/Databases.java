package com.aurora.core.models;

import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.DATABASES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.experimental.SuperBuilder;

import com.aurora.core.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder(toBuilder = true)
@Entity(tableName = DATABASES, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME}, unique = true)})
public class Databases extends Item {

  @Ignore
  public Databases() {
    super();
  }

  public Databases(String name,
      String source,
      String idAsNameBackup) {
    super(name, source, idAsNameBackup);
  }

  public Databases clone() {
    //return Databases.builder().name(getName()).source(getSource()).idAsNameBackup(getIdAsNameBackup()).build(); //todo wait for lombok plugin update that will support @SuperBuilder
    return new Databases(
        getName(),
        getSource(),
        getIdAsNameBackup());
  }
}

