package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Rules;

import androidx.room.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(tableName = "RulesCombat")
public class RulesCombat extends Rules {

}
