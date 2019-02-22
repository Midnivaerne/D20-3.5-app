package com.aurora.player.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aurora.d20_35_app.R;
import com.aurora.d20_35_app.models.userData.HeroStatistics;
import com.aurora.player.viewModels.PlayerCharacterVM;

import androidx.fragment.app.Fragment;

import static com.aurora.d20_35_app.database.TranslationsHolder.translate;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlayerCharacterStatisticsPlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private static final String DESCRIPTIONS[] = {
    };

    private static final int ID_VALUES[] = {
            R.id.fragment_player_character_statistics_combat_hit_points,
    };

    private static final String HERO_STATISTIC_STR_NAME_DESCRIPTION = "hero_statistic_str_name_description";
    private static final String HERO_STATISTIC_DEX_NAME_DESCRIPTION = "hero_statistic_dex_name_description";
    private static final String HERO_STATISTIC_CON_NAME_DESCRIPTION = "hero_statistic_con_name_description";
    private static final String HERO_STATISTIC_INT_NAME_DESCRIPTION = "hero_statistic_int_name_description";
    private static final String HERO_STATISTIC_WIS_NAME_DESCRIPTION = "hero_statistic_wis_name_description";
    private static final String HERO_STATISTIC_CHA_NAME_DESCRIPTION = "hero_statistic_cha_name_description";

    private static final String STATISTICS_DESCRIPTIONS[] = {
            HERO_STATISTIC_STR_NAME_DESCRIPTION,
            HERO_STATISTIC_DEX_NAME_DESCRIPTION,
            HERO_STATISTIC_CON_NAME_DESCRIPTION,
            HERO_STATISTIC_INT_NAME_DESCRIPTION,
            HERO_STATISTIC_WIS_NAME_DESCRIPTION,
            HERO_STATISTIC_CHA_NAME_DESCRIPTION};

    private static final int ID_STATISTICS_VALUES[] = {
            R.id.fragment_player_character_statistics_ability_scores_str,
            R.id.fragment_player_character_statistics_ability_scores_dex,
            R.id.fragment_player_character_statistics_ability_scores_con,
            R.id.fragment_player_character_statistics_ability_scores_int,
            R.id.fragment_player_character_statistics_ability_scores_wis,
            R.id.fragment_player_character_statistics_ability_scores_cha,
    };

    private PlayerCharacterVM playerCharacterVM;

    public PlayerCharacterStatisticsPlaceholderFragment() {
    }

    public static PlayerCharacterStatisticsPlaceholderFragment newInstance(int sectionNumber, PlayerCharacterVM playerCharacterVM) {
        PlayerCharacterStatisticsPlaceholderFragment fragment = new PlayerCharacterStatisticsPlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.playerCharacterVM = playerCharacterVM;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_character_statistics, container, false);
        loadHeroDataFromVMtoView(rootView);
        return rootView;
    }

    private void loadHeroDataFromVMtoView(View rootView) {

        for (int i = 0; i < ID_STATISTICS_VALUES.length; i++) {
            LinearLayout entryOuter = (LinearLayout) rootView.findViewById(ID_STATISTICS_VALUES[i]);
            ((TextView) entryOuter.findViewById(R.id.fragment_player_character_statistics_ability_scores_score_name)).setText(translate(STATISTICS_DESCRIPTIONS[i]));
            ((TextView) entryOuter.findViewById(R.id.fragment_player_character_statistics_ability_scores_score_value)).setText(playerCharacterVM.getHeroStatisticsTextValues()[i]);
            ((TextView) entryOuter.findViewById(R.id.fragment_player_character_statistics_ability_scores_score_modifier)).setText(String.valueOf(HeroStatistics.getStatisticModifier(Integer.parseInt(playerCharacterVM.getHeroStatisticsTextValues()[i]))));

            //todo add modifiers, don't show if the same as base values
            ((TextView) entryOuter.findViewById(R.id.fragment_player_character_statistics_ability_scores_score_temporary_value)).setText(playerCharacterVM.getHeroStatisticsTextValues()[i]);
            ((TextView) entryOuter.findViewById(R.id.fragment_player_character_statistics_ability_scores_score_temporary_modifier)).setText(String.valueOf(HeroStatistics.getStatisticModifier(Integer.parseInt(playerCharacterVM.getHeroStatisticsTextValues()[i]))));
        }
    }

}
