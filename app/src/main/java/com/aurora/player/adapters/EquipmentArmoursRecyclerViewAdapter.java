package com.aurora.player.adapters;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.aurora.core.models.userdata.HeroArmour;
import com.aurora.core.models.userdata.HeroPlayer;

public class EquipmentArmoursRecyclerViewAdapter extends RecyclerView.Adapter<EquipmentArmoursRecyclerViewAdapter.ViewHolder> {

  private HeroPlayer playerHero;
  private final Map<Integer, HeroArmour> heroArmourMapOnView = new HashMap<>();

  public EquipmentArmoursRecyclerViewAdapter(HeroPlayer playerHero) {
    this.playerHero = playerHero;
    generateIds();
  }

  private void generateIds() {
    List<HeroArmour> sortedList = new ArrayList<>(playerHero.getHeroArmour());
    Collections.sort(sortedList, new EquipmentArmoursRecyclerViewAdapter.HeroArmourComparator());
    int i = 1;
    for (HeroArmour heroArmour : sortedList) {
      heroArmourMapOnView.put(i, heroArmour);
      i++;
    }
  }

  private final View.OnClickListener onClickListener = view -> {
  };

  @Override
  public EquipmentArmoursRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_player_character_equipment_armour, parent, false);
    return new EquipmentArmoursRecyclerViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull EquipmentArmoursRecyclerViewAdapter.ViewHolder holder, int position) {
    HeroArmour heroArmour = heroArmourMapOnView.get(position + 1);

    holder.armourName.setText(translate(heroArmour.getName()));
    holder.armourName.setTag(heroArmour);
    holder.armourName.setOnClickListener(onClickListener);

    holder.armourAc.setTag(heroArmour);
    holder.armourAc.setOnClickListener(onClickListener);

    holder.armourDex.setTag(heroArmour);
    holder.armourDex.setOnClickListener(onClickListener);

    holder.armourPropertiesDescription.setTag(heroArmour);
    holder.armourPropertiesDescription.setOnClickListener(onClickListener);

    holder.armourPropertiesValues.setTag(heroArmour);
    holder.armourPropertiesValues.setOnClickListener(onClickListener);
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final TextView armourName;
    final TextView armourAc;
    final TextView armourDex;
    final TextView armourPropertiesDescription;
    final TextView armourPropertiesValues;

    ViewHolder(View view) {
      super(view);
      armourName = (TextView) view.findViewById(R.id.fragment_player_character_equipment_armour_name);
      armourAc = (TextView) view.findViewById(R.id.fragment_player_character_equipment_armour_ac);
      armourDex = (TextView) view.findViewById(R.id.fragment_player_character_equipment_armour_dex);
      armourPropertiesDescription = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_armour_properties)
          .findViewById(R.id.fragment_number_value_with_description_box_description));
      armourPropertiesValues = (TextView) (view.findViewById(R.id.fragment_player_character_equipment_armour_properties)
          .findViewById(R.id.fragment_number_value_with_description_box_value));
    }
  }

  private class HeroArmourComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
      return ((HeroArmour) o1).getName().compareTo(((HeroArmour) o2).getName());
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
