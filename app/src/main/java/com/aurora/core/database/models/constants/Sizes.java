package com.aurora.core.database.models.constants;

import static com.aurora.core.database.DbTableNames.SIZES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = SIZES, inheritSuperIndices = true)
public class Sizes extends Rules {

  public Sizes() {
    super();
  }

  public Sizes(String name) {
    super(name);
  }

  public Sizes(Sizes source) {
    new Sizes(
        source.getName());
  }
}
