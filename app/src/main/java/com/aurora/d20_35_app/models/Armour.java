package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(tableName = "Armour", inheritSuperIndices = true)
public class Armour extends Item {

    @Ignore
    public static final String armourPriceColumnName = "ArmourPrice";
    @Ignore
    public static final String armourDeflectionColumnName = "ArmourDeflection";
    @Ignore
    public static final String armourMaxDexterityBonusColumnName = "ArmourMaxDexterityBonus";
    @Ignore
    public static final String armourPenaltyColumnName = "ArmourPenalty";
    @Ignore
    public static final String armourArcaneFailureColumnName = "ArmourArcaneFailure";
    @Ignore
    public static final String armourMaxSpeedColumnName = "ArmourMaxSpeed";
    @Ignore
    public static final String armourWeightColumnName = "ArmourWeight";
    @Ignore
    public static final String armourSpecialPropertiesColumnName = "ArmourSpecialProperties";
    @Ignore
    public static final String armourMaterialColumnName = "ArmourMaterial";
    @Ignore
    public static final String armourMagicImprovementsColumnName = "ArmourMagicImprovements";

    @Getter
    @Setter
    @ColumnInfo(name = armourPriceColumnName)
    private String armourPrice;

    @Getter
    @Setter
    @ColumnInfo(name = armourDeflectionColumnName)
    private String armourDeflection;

    @Getter
    @Setter
    @ColumnInfo(name = armourMaxDexterityBonusColumnName)
    private String armourMaxDexterityBonus;

    @Getter
    @Setter
    @ColumnInfo(name = armourPenaltyColumnName)
    private String armourPenalty;

    @Getter
    @Setter
    @ColumnInfo(name = armourArcaneFailureColumnName)
    private String armourArcaneFailure;

    @Getter
    @Setter
    @ColumnInfo(name = armourMaxSpeedColumnName)
    private String armourMaxSpeed;

    @Getter
    @Setter
    @ColumnInfo(name = armourWeightColumnName)
    private String armourWeight;

    @Getter
    @Setter
    @ColumnInfo(name = armourSpecialPropertiesColumnName)
    private String armourSpecialProperties;

    @Getter
    @Setter
    @ColumnInfo(name = armourMaterialColumnName)
    private String armourMaterial;

    @Getter
    @Setter
    @ColumnInfo(name = armourMagicImprovementsColumnName)
    private String armourMagicImprovements;
}
