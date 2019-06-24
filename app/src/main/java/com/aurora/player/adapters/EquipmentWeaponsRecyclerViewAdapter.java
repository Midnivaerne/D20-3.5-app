package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.models.userdata.HeroPlayer;
import com.aurora.core.models.userdata.HeroWeapons;

public class EquipmentWeaponsRecyclerViewAdapter extends RecyclerView.Adapter<EquipmentWeaponsRecyclerViewAdapter.ViewHolder> {

  private HeroPlayer playerHero;
  private final Map<Integer, HeroWeapons> heroWeaponsMapOnView = new HashMap<>();

  public EquipmentWeaponsRecyclerViewAdapter(HeroPlayer playerHero) {
    this.playerHero = playerHero;
    generateIds();
  }

  private void generateIds() {
    List<HeroWeapons> sortedList = new ArrayList<>(playerHero.getHeroWeapons());
    Collections.sort(sortedList, new EquipmentWeaponsRecyclerViewAdapter.HeroWeaponsComparator());
    int i = 1;
    for (HeroWeapons heroWeapons : sortedList) {
      heroWeaponsMapOnView.put(i, heroWeapons);
      i++;
    }
  }

  private final View.OnClickListener onClickListener = view -> {

  };

  private final View.OnClickListener onClickExpanderListener = view -> {

  };

  @Override
  public EquipmentWeaponsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.fragment_player_character_equipment_weapons_weapon, parent, false);
    return new EquipmentWeaponsRecyclerViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull EquipmentWeaponsRecyclerViewAdapter.ViewHolder holder, int position) {
    HeroWeapons heroWeapon = heroWeaponsMapOnView.get(position + 1);

    holder.weaponEquippedIndicator.setTag(heroWeapon);
    holder.weaponEquippedIndicator.setOnClickListener(onClickListener);

    holder.weaponName.setText(translate(heroWeapon.getName()));
    holder.weaponName.setTag(heroWeapon);
    holder.weaponName.setOnClickListener(onClickListener);

    holder.weaponProperties.setTag(heroWeapon);
    holder.weaponProperties.setOnClickListener(onClickListener);

    holder.weaponAttackDescription.setTag(heroWeapon);
    holder.weaponAttackDescription.setOnClickListener(onClickListener);

    holder.weaponAttackValue.setTag(heroWeapon);
    holder.weaponAttackValue.setOnClickListener(onClickListener);

    holder.weaponDamageDescription.setTag(heroWeapon);
    holder.weaponDamageDescription.setOnClickListener(onClickListener);

    holder.weaponDamageValue.setTag(heroWeapon);
    holder.weaponDamageValue.setOnClickListener(onClickListener);

    holder.weaponQuantity.setTag(heroWeapon);
    holder.weaponQuantity.setOnClickListener(onClickListener);

    holder.weaponRangedExpander.setTag(heroWeapon);
    holder.weaponRangedExpander.setOnClickListener(onClickExpanderListener);
  }

  @Override
  public int getItemCount() {
    return playerHero.getHeroWeapons().size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final ImageButton weaponEquippedIndicator;
    final TextView weaponName;
    final TextView weaponProperties;
    final TextView weaponAttackDescription;
    final TextView weaponAttackValue;
    final TextView weaponDamageDescription;
    final TextView weaponDamageValue;
    final TextView weaponQuantity;
    final ImageButton weaponRangedExpander;

    ViewHolder(View view) {
      super(view);
      weaponEquippedIndicator = (ImageButton) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_equipped);
      weaponName = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_name);
      weaponProperties = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_properties);
      weaponAttackDescription = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_attack)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_description));
      weaponAttackValue = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_attack)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_value));
      weaponDamageDescription = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_damage)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_description));
      weaponDamageValue = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_damage)
          .findViewById(R.id.fragment_number_value_with_description_rollable_box_value));
      weaponQuantity = (TextView) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_texts_quantity);
      weaponRangedExpander = (ImageButton) view.findViewById(R.id.fragment_player_character_equipment_weapons_weapon_ranged_show);
    }
  }

  private class HeroWeaponsComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
      return ((HeroWeapons) o1).getName().compareTo(((HeroWeapons) o2).getName());
    }

    @Override
    public boolean equals(Object obj) {
      return false;
    }

    @Override
    public int hashCode() {
      return 0;
    }
  }
}
