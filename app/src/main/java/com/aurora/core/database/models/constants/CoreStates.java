package com.aurora.core.database.models.constants;

import static com.aurora.core.database.DbTableNames.CORE_STATES;

import lombok.Data;
import lombok.EqualsAndHashCode;

import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.helpers.Rules;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = CORE_STATES, inheritSuperIndices = true)
public class CoreStates extends Rules {

  public CoreStates() {
    super();
  }

  public CoreStates(String name) {
    super(name);
  }

  public CoreStates(CoreStates source) {
    new CoreStates(source.getName());
  }
}
