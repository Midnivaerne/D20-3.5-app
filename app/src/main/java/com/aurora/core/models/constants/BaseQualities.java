package com.aurora.core.models.constants;

import static com.aurora.core.database.DbTableNames.BASE_QUALITIES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;

import com.aurora.core.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = BASE_QUALITIES, inheritSuperIndices = true)
public class BaseQualities extends Rules {

  @Ignore
  public BaseQualities() {
    super();
  }

  public BaseQualities(String name) {
    super(name);
  }

  public BaseQualities clone() {
    return new BaseQualities(getName());
  }
}
