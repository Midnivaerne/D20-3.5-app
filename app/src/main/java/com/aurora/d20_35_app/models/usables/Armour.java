package com.aurora.d20_35_app.models.usables;

import com.aurora.d20_35_app.models.helpers.Item;
import com.aurora.d20_35_app.models.Databases;

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
        foreignKeys = @ForeignKey(entity = Databases.class, parentColumns = "Source", childColumns = "Source", onDelete = ForeignKey.CASCADE))
public class Armour extends Item {

    @Ignore
    public Armour() {
        super();
    }

    public Armour(String name,
                  String source,
                  String idAsNameBackup,
                  String armourPrice,
                  String armourDeflection,
                  String armourMaxDexterityBonus,
                  String armourPenalty,
                  String armourArcaneFailure,
                  String armourMaxSpeed,
                  String armourWeight,
                  String armourSpecialProperties,
                  String armourMaterial,
                  String armourMagicImprovements) {
        super(name, source, idAsNameBackup);
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
    public static final String ARMOUR_PRICE_COLUMN_NAME = "ArmourPrice";
    @Ignore
    public static final String ARMOUR_DEFLECTION_COLUMN_NAME = "ArmourDeflection";
    @Ignore
    public static final String ARMOUR_MAX_DEXTERITY_BONUS_COLUMN_NAME = "ArmourMaxDexterityBonus";
    @Ignore
    public static final String ARMOUR_PENALTY_COLUMN_NAME = "ArmourPenalty";
    @Ignore
    public static final String ARMOUR_ARCANE_FAILURE_COLUMN_NAME = "ArmourArcaneFailure";
    @Ignore
    public static final String ARMOUR_MAX_SPEED_COLUMN_NAME = "ArmourMaxSpeed";
    @Ignore
    public static final String ARMOUR_WEIGHT_COLUMN_NAME = "ArmourWeight";
    @Ignore
    public static final String ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME = "ArmourSpecialProperties";
    @Ignore
    public static final String ARMOUR_MATERIAL_COLUMN_NAME = "ArmourMaterial";
    @Ignore
    public static final String ARMOUR_MAGIC_IMPROVEMENTS_COLUMN_NAME = "ArmourMagicImprovements";

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_PRICE_COLUMN_NAME)
    private String armourPrice;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_DEFLECTION_COLUMN_NAME)
    private String armourDeflection;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_MAX_DEXTERITY_BONUS_COLUMN_NAME)
    private String armourMaxDexterityBonus;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_PENALTY_COLUMN_NAME)
    private String armourPenalty;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_ARCANE_FAILURE_COLUMN_NAME)
    private String armourArcaneFailure;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_MAX_SPEED_COLUMN_NAME)
    private String armourMaxSpeed;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_WEIGHT_COLUMN_NAME)
    private String armourWeight;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_SPECIAL_PROPERTIES_COLUMN_NAME)
    private String armourSpecialProperties;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_MATERIAL_COLUMN_NAME)
    private String armourMaterial;

    @Getter
    @Setter
    @ColumnInfo(name = ARMOUR_MAGIC_IMPROVEMENTS_COLUMN_NAME)
    private String armourMagicImprovements;

    public Armour clone() {
        return new Armour(
                getName(),
                getSource(),
                getIdAsNameBackup(),
                getArmourPrice(),
                getArmourDeflection(),
                getArmourMaxDexterityBonus(),
                getArmourPenalty(),
                getArmourArcaneFailure(),
                getArmourMaxSpeed(),
                getArmourWeight(),
                getArmourSpecialProperties(),
                getArmourMaterial(),
                getArmourMagicImprovements());
    }
}
