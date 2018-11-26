package com.aurora.d20_35_app.enums;

public enum RulesType {
    /**
     * RulesCombat
     */
    RulesCombat("RulesCombat"),
    /**
     * RulesSkills
     */
    RulesSkills("RulesSkills");

    private String rulesType;

    RulesType(String rulesType) {
        this.rulesType = rulesType;
    }

    @Override
    public String toString() {
        return this.rulesType;
    }

}
