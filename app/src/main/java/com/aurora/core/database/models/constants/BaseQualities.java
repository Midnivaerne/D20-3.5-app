package com.aurora.core.database.models.constants;

import static com.aurora.core.database.DbTableNames.BASE_QUALITIES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = BASE_QUALITIES, inheritSuperIndices = true)
public class BaseQualities extends Rules {

  public BaseQualities() {
    super();
  }

  public BaseQualities(String name) {
    super(name);
  }

  public BaseQualities(BaseQualities source) {
    new BaseQualities(
        source.getName());
  }
}
