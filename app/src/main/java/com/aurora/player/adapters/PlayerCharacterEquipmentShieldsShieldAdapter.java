package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.database.models.userdata.HeroArmour;
import com.aurora.core.database.models.userdata.HeroPlayer;
import com.aurora.player.playercharacterutils.PlayerCharacterArmourEnum;
import com.aurora.player.playercharacterutils.PlayerCharacterArmourSpecificEnum;

public class PlayerCharacterEquipmentShieldsShieldAdapter extends Adapter {

  private final Context context;
  private final HeroPlayer heroPlayer;

  private RecyclerView shieldsRecyclerView;
  private Map<Integer, Integer> shieldListIdToItemId;
  private Map<Integer, Integer> shieldItemIdToListId;

  public PlayerCharacterEquipmentShieldsShieldAdapter(Context context, RecyclerView shieldsRecyclerView, HeroPlayer heroPlayer) {
    this.context = context;
    this.heroPlayer = heroPlayer;
    this.shieldsRecyclerView = shieldsRecyclerView;

    generateLists();
  }

  private void generateLists() {
    shieldListIdToItemId = new HashMap<Integer, Integer>();
    shieldItemIdToListId = new HashMap<Integer, Integer>();
    if (heroPlayer.getHeroArmourFromIdMap() != null && !heroPlayer.getHeroArmourFromIdMap().isEmpty()) {
      int i = 0;
      if (heroPlayer.getRightHandHeldItemId() != null && heroPlayer.getHeroArmourFromIdMap()
          .containsKey(heroPlayer.getRightHandHeldItemId())) {
        shieldListIdToItemId.put(i, heroPlayer.getRightHandHeldItemId());
        shieldItemIdToListId.put(heroPlayer.getRightHandHeldItemId(), i);
        i++;
      }
      if (heroPlayer.getLeftHandHeldItemId() != null && heroPlayer.getHeroArmourFromIdMap()
          .containsKey(heroPlayer.getLeftHandHeldItemId())) {
        shieldListIdToItemId.put(i, heroPlayer.getLeftHandHeldItemId());
        shieldItemIdToListId.put(heroPlayer.getLeftHandHeldItemId(), i);
        i++;
      }
      for (Integer id : heroPlayer.getHeroArmourFromIdMap().keySet()) {
        if (heroPlayer.getHeroArmourFromIdMap().get(id).getArmour().getArmourType().getIsShield() &&
            !id.equals(heroPlayer.getRightHandHeldItemId()) && !id.equals(heroPlayer.getLeftHandHeldItemId())) {
          shieldListIdToItemId.put(i, id);
          shieldItemIdToListId.put(id, i);
          i++;
        }
      }
    }
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_player_character_equipment_armour, parent, false);
    ShieldViewHolder shieldViewHolder = new ShieldViewHolder(view);
    view.setTag(shieldViewHolder);
    return shieldViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ((ShieldViewHolder) holder).bind(position);
  }

  @Override
  public int getItemCount() {
    return shieldListIdToItemId == null ? 0 : shieldListIdToItemId.size();
  }


  private class ShieldViewHolder extends RecyclerView.ViewHolder {

    View armorView;

    ShieldViewHolder(@NonNull View armorView) {
      super(armorView);
      this.armorView = armorView;
    }

    public void bind(int position) {
      HeroArmour heroShield = heroPlayer.getHeroArmourFromIdMap().get(shieldListIdToItemId.get(position));
      if (heroShield != null && heroShield.getArmourValues() != null) {
        Map<PlayerCharacterArmourEnum, String> heroArmourValues = heroShield.getArmourValues();
        for (PlayerCharacterArmourEnum armourEnum : PlayerCharacterArmourEnum.values()) {
          if (heroArmourValues.get(armourEnum) != null) {
            for (PlayerCharacterArmourSpecificEnum armourSpecific : PlayerCharacterArmourSpecificEnum.values()) {
              TextView textView = (TextView) armorView.findViewById(armourEnum.getFieldId())
                  .findViewById(armourSpecific.getSpecificFieldId(armourEnum));
              textView.setText(translate(armourSpecific.getSpecificValue(armourEnum, heroShield)));
              textView.setTag(heroShield);
              //textView.setOnClickListener(onClickListener);
            }
          }
        }
      }
    }
  }
}
