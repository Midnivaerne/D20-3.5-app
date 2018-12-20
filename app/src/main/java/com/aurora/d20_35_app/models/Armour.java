package com.aurora.d20_35_app.models;

import com.aurora.d20_35_app.helper.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(tableName = "Armour", inheritSuperIndices = true,
        indices = {@Index(value = {"Source"})},
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source",onDelete = ForeignKey.CASCADE))
public class Armour extends Item {

    @Ignore
    public Armour() {
        super();
    }

    public Armour(String name, String source, String armourPrice, String armourDeflection, String armourMaxDexterityBonus, String armourPenalty, String armourArcaneFailure, String armourMaxSpeed, String armourWeight, String armourSpecialProperties, String armourMaterial, String armourMagicImprovements) {
        super(name, source);
        this.armourPrice = armourPrice;
        this.armourDeflection = armourDeflection;
        this.armourMaxDexterityBonus = armourMaxDexterityBonus;
        this.armourPenalty = armourPenalty;
        this.armourArcaneFailure = armourArcaneFailure;
        this.armourMaxSpeed = armourMaxSpeed;
        this.armourWeight = armourWeight;
        this.armourSpecialProperties = armourSpecialProperties;
        this.armourMaterial = armourMaterial;
        this.armourMagicImprovements = armourMagicImprovements;
    }

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

    public Armour clone(){
        return new Armour(getName(), getSource(), this.armourPrice, this.armourDeflection, this.armourMaxDexterityBonus, this.armourPenalty, this.armourArcaneFailure, this.armourMaxSpeed, this.armourWeight, this.armourSpecialProperties, this.armourMaterial, this.armourMagicImprovements);
    }
}
