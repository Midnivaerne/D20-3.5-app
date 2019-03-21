package com.aurora.core.models.constants;

import static com.aurora.core.database.DBTableNames.SIZES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import com.aurora.core.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = SIZES, inheritSuperIndices = true)
public class Sizes extends Rules {

  @Ignore
  public Sizes() {
    super();
  }

  public Sizes(String name) {
    super(name);
  }

  public Sizes clone() {
    return new Sizes(getName());
  }
}
