package com.aurora.player.fragments;

import static com.aurora.core.database.TranslationsHolder.translate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

import com.aurora.core.R;
import com.aurora.core.models.helpers.Item;
import com.aurora.core.models.settingspecific.Skills;
import com.aurora.player.viewmodels.PlayerCharacterVM;
import com.aurora.player.views.PlayerCharacterActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterSkillsPlaceholderFragment extends Fragment {

  /**
   * The fragment argument representing the section number for this fragment.
   */
  private static final String ARG_SECTION_NUMBER = "section_number";

  private PlayerCharacterVM playerCharacterVM;

  public PlayerCharacterSkillsPlaceholderFragment() {
  }

  /**
   * Returns a new instance of this fragment for the given section number.
   */
  public static PlayerCharacterSkillsPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
    PlayerCharacterSkillsPlaceholderFragment fragment = new PlayerCharacterSkillsPlaceholderFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_SECTION_NUMBER, sectionNumber);
    fragment.setArguments(args);
    fragment.playerCharacterVM = playerCharacterVM;
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_player_character_skills, container, false);
    loadHeroDataFromVMtoView(rootView);
    return rootView;
  }

  private void loadHeroDataFromVMtoView(View rootView) {
    Map<Skills, Integer> skillsList = playerCharacterVM.getHero().getHeroSkills().getSkillListAsSkillAndRank(); //todo include also skills without ranks
    for (Skills skill : skillsList.keySet()) {
      //((TextView) rootView.findViewById(fieldBase.getFieldId()).findViewById(specificBase.getSpecificFieldId(fieldBase)))
      //    .setText(skillsList.get(skill));
    }

    View recyclerView = rootView.findViewById(R.id.fragment_player_character_skills_layout);
    assert recyclerView != null;
    ((RecyclerView) recyclerView)
        .setAdapter(new SkillsRecyclerViewAdapter(skillsList));
  }

  public static class SkillsRecyclerViewAdapter extends RecyclerView.Adapter<SkillsRecyclerViewAdapter.ViewHolder> {

    private final Map<Skills, Integer> heroSkillsMap;
    private final Map<Integer, Skills> skillsMapOnView = new HashMap<>();
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Context context = view.getContext();
        //Intent intent = new Intent(context, PlayerCharacterSkillDetailActivity.class);
        //intent.putExtra(PlayerCharacterSkillDetailFragment.ARG_ITEM_ID, getListId(view));
        //intent.putExtra(PlayerCharacterActivity.HERO_PLAYER_ID, getSkillId(view));
        //context.startActivity(intent);
        //todo popup window with skill values and description
      }
    };

    private final View.OnClickListener playerCharactersListItemButtonOnClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, PlayerCharacterActivity.class);
        intent.putExtra(PlayerCharacterActivity.HERO_PLAYER_ID, getSkillId(view));
        context.startActivity(intent);
      }
    };

    SkillsRecyclerViewAdapter(Map<Skills, Integer> heroSkillsMap) {
      this.heroSkillsMap = heroSkillsMap;
      int i = 1;
      for (Skills skill : heroSkillsMap.keySet()) {
        skillsMapOnView.put(i, skill);
        i++;
      }
    }

    private String getListId(View view) {
      Item skill = (Skills) view.getTag();
      int listId = skill.getItemID();
      return String.valueOf(listId);
    }

    private String getSkillId(View view) {
      return String.valueOf(((Skills) view.getTag()).getItemID());
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext())
          .inflate(R.layout.fragment_player_character_skills_skill, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
      Skills skill = skillsMapOnView.get(position + 1);
      String rank = String.valueOf(heroSkillsMap.get(skill));

      //holder.skillProficiency.setImageDrawable(); //todo set/hide image
      holder.skillProficiency.setTag(skill);
      holder.skillProficiency.setOnClickListener(onClickListener);

      holder.skillName.setText(translate(skill.getName()));
      holder.skillName.setTag(skill);
      holder.skillName.setOnClickListener(onClickListener);

      //holder.skillCanUntrained.setImageDrawable(); //todo set/hide image
      holder.skillCanUntrained.setTag(skill);
      holder.skillCanUntrained.setOnClickListener(onClickListener);

      holder.skillAttribute.setText(skill.getSkillAttribute());
      holder.skillAttribute.setTag(skill);
      holder.skillAttribute.setOnClickListener(onClickListener);

      //holder.skillArmourPenalty.setImageDrawable((); //todo set/hide image
      holder.skillArmourPenalty.setTag(skill);
      holder.skillArmourPenalty.setOnClickListener(onClickListener);

      holder.skillValue.setText(rank); //todo add modifiers
      holder.skillValue.setTag(skill);
      holder.skillValue.setOnClickListener(onClickListener);

    }

    @Override
    public int getItemCount() {
      return heroSkillsMap.size();
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
  }
}
