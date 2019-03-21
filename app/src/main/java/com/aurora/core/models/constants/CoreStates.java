package com.aurora.core.models.constants;

import static com.aurora.core.database.DBTableNames.CORE_STATES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import com.aurora.core.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = CORE_STATES, inheritSuperIndices = true)
public class CoreStates extends Rules {

  @Ignore
  public CoreStates() {
    super();
  }

  public CoreStates(String name) {
    super(name);
  }

  public CoreStates clone() {
    return new CoreStates(getName());
  }
}
