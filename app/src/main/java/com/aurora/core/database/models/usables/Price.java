package com.aurora.core.database.models.usables;

import static com.aurora.core.database.DbColumnNames.PRICE_CURRENCY_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.PRICE_VALUE_COLUMN_NAME;
import static com.aurora.core.database.DbColumnNames.SOURCE_COLUMN_NAME;
import static com.aurora.core.database.DbTableNames.PRICE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import com.aurora.core.database.models.Databases;
import com.aurora.core.database.models.helpers.Item;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@Entity(tableName = PRICE, inheritSuperIndices = true,
    indices = {@Index(value = {SOURCE_COLUMN_NAME})},
    foreignKeys = {
        @ForeignKey(entity = Databases.class, parentColumns = SOURCE_COLUMN_NAME, childColumns = SOURCE_COLUMN_NAME, onDelete = ForeignKey.CASCADE)}
)
public class Price extends Item {

  @ColumnInfo(name = PRICE_VALUE_COLUMN_NAME)
  private Integer priceValue;

  @ColumnInfo(name = PRICE_CURRENCY_COLUMN_NAME)
  private String priceCurrency;

  @Ignore
  public Price() {
    super();
  }

  @Ignore
  public Price(String name,
      String source,
      String idAsNameBackup) {
    new Price(name, source, idAsNameBackup, null, null);
  }

  public Price(String name,
      String source,
      String idAsNameBackup,
      Integer priceValue,
      String priceCurrency) {
    super(name, source, idAsNameBackup);
    this.setPriceValue(priceValue);
    this.setPriceCurrency(priceCurrency);
  }

  @Ignore
  public Price(Price source) {
    new Price(
        source.getName(),
        source.getSource(),
        source.getIdAsNameBackup(),
        source.getPriceValue(),
        source.getPriceCurrency());
  }

}
