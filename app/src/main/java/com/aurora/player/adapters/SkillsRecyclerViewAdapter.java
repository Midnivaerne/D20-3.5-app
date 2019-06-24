package com.aurora.player.adapters;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static com.aurora.core.database.TranslationsHolder.translate;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.models.settingspecific.Skills;
import com.aurora.core.models.userdata.HeroPlayer;
import com.aurora.player.playercharacterutils.PlayerCharacterSkillsValues;

public class SkillsRecyclerViewAdapter extends RecyclerView.Adapter<SkillsRecyclerViewAdapter.ViewHolder> {

  private HeroPlayer playerHero;
  private final Map<Integer, Skills> allSkillsMapOnView = new HashMap<>();
  private final Map<Integer, Skills> heroSkillsMapOnView = new HashMap<>();

  public SkillsRecyclerViewAdapter(HeroPlayer playerHero) {
    this.playerHero = playerHero;
    generateIds();
  }

  void generateIds() {
    List<Skills> sortedList = new ArrayList<>(playerHero.getHeroSkills().getAllSettingSkillsWithOtherModifiers().keySet());
    Collections.sort(sortedList, new SkillsRecyclerViewAdapter.SkillsComparator());
    int i = 1;
    for (Skills skill : sortedList) {
      allSkillsMapOnView.put(i, skill);
      if (playerHero.getHeroSkills().getSkillListAsSkillAndRank().keySet().contains(skill)) {
        heroSkillsMapOnView.put(i, skill);
      }
      i++;
    }
  }

  private final View.OnClickListener onClickListener = view -> {
    LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
    View popupView = inflater.inflate(R.layout.popup_window_skills_detail, null);
    Skills skill = (Skills) view.getTag();
    ((TextView) popupView.findViewById(R.id.skill_detail_name)).setText(translate(skill.getName()));
    ((TextView) popupView.findViewById(R.id.skill_detail_value)).setText(String.valueOf(
        playerHero.getHeroSkills().getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.RANK) + playerHero.getHeroSkills()
            .getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.ATTRIBUTE_MODIFIER) + playerHero.getHeroSkills()
            .getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.OTHER)));
    ((TextView) popupView.findViewById(R.id.skill_detail_rank))
        .setText(String.valueOf(playerHero.getHeroSkills().getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.RANK)));
    ((TextView) popupView.findViewById(R.id.skill_detail_attribute_modifier)).setText(
        String.valueOf(playerHero.getHeroSkills().getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.ATTRIBUTE_MODIFIER)));
    ((TextView) popupView.findViewById(R.id.skill_detail_other_modifier))
        .setText(String.valueOf(playerHero.getHeroSkills().getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.OTHER)));
    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
    boolean focusable = true;
    final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    popupWindow.setElevation(20);
    popupView.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        popupWindow.dismiss();
        return true;
      }
    });
  };

  @Override
  public SkillsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_player_character_skills_skill, parent, false);
    return new SkillsRecyclerViewAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull final SkillsRecyclerViewAdapter.ViewHolder holder, int position) {
    Skills skill = allSkillsMapOnView.get(position + 1);
    if (heroSkillsMapOnView.containsValue(skill)) {
      //holder.skillProficiency.setImageDrawable(); //todo set proficient image
    } else {
      //holder.skillProficiency.setImageDrawable(); //todo set not proficient image
    }
    holder.skillProficiency.setTag(skill);
    holder.skillProficiency.setOnClickListener(onClickListener);

    if (skill.getSkillCanHaveSubskills() != null && skill.getSkillCanHaveSubskills().equals("true")) {
      holder.skillName.setText(
          new StringBuilder().append(translate(skill.getName())).append(" (").append(skill.getSkillSubskill()).append(")").toString());
    } else {
      holder.skillName.setText(translate(skill.getName()));
    }
    holder.skillName.setTag(skill);
    holder.skillName.setOnClickListener(onClickListener);

    if (skill.getSkillExclusive().equals("false")) {
      holder.skillCanUntrained.setImageDrawable(null);
    }
    holder.skillCanUntrained.setTag(skill);
    holder.skillCanUntrained.setOnClickListener(onClickListener);

    holder.skillAttribute.setText(skill.getSkillAttribute());
    holder.skillAttribute.setTag(skill);
    holder.skillAttribute.setOnClickListener(onClickListener);

    if (skill.getSkillUseArmourPenalty().equals("false")) {
      holder.skillArmourPenalty.setImageDrawable(null);
    }
    holder.skillArmourPenalty.setTag(skill);
    holder.skillArmourPenalty.setOnClickListener(onClickListener);

    holder.skillValue.setText(String.valueOf(
        playerHero.getHeroSkills().getValuesHolder().get(skill).get(PlayerCharacterSkillsValues.RANK) + playerHero.getHeroSkills()
            .getValuesHolder().get(skill)
            .get(PlayerCharacterSkillsValues.ATTRIBUTE_MODIFIER) + playerHero.getHeroSkills().getValuesHolder().get(skill)
            .get(PlayerCharacterSkillsValues.OTHER)));
    holder.skillValue.setTag(skill);
    holder.skillValue.setOnClickListener(onClickListener);

  }

  @Override
  public int getItemCount() {
    return playerHero.getHeroSkills().getAllSettingSkillsWithOtherModifiers().size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final ImageView skillProficiency;
    final TextView skillName;
    final ImageView skillCanUntrained;
    final TextView skillAttribute;
    final ImageView skillArmourPenalty;
    final TextView skillValue;

    ViewHolder(View view) {
      super(view);
      skillProficiency = (ImageView) view.findViewById(R.id.fragment_player_character_skills_skill_proficiency);
      skillName = (TextView) view.findViewById(R.id.fragment_player_character_skills_skill_name);
      skillCanUntrained = (ImageView) view.findViewById(R.id.fragment_player_character_skills_skill_can_untrained);
      skillAttribute = (TextView) view.findViewById(R.id.fragment_player_character_skills_skill_attribute);
      skillArmourPenalty = (ImageView) view.findViewById(R.id.fragment_player_character_skills_skill_armour_penalty);
      skillValue = (TextView) view.findViewById(R.id.fragment_player_character_skills_skill_value);
    }
  }

  private class SkillsComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
      return ((Skills) o1).getName().compareTo(((Skills) o2).getName());
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
