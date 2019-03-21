package com.aurora.core.models.constants;

import static com.aurora.core.database.DBTableNames.ALIGNMENTS;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import com.aurora.core.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = ALIGNMENTS, inheritSuperIndices = true)
public class Alignments extends Rules {

  @Ignore
  public Alignments() {
    super();
  }

  public Alignments(String name) {
    super(name);
  }

  public Alignments clone() {
    return new Alignments(getName());
  }
}
