package com.aurora.d20_35_app.models.typeHelpers;

import static com.aurora.d20_35_app.models.typeHelpers.RulesType.RULES_COMBAT;
import static com.aurora.d20_35_app.models.typeHelpers.RulesType.RULES_SKILLS;
import static com.aurora.d20_35_app.models.typeHelpers.RulesType.contains;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.database.DatabaseManager;
import com.aurora.d20_35_app.models.constants.RulesCombat;
import com.aurora.d20_35_app.models.constants.RulesSkills;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesTypeTest {


    private DatabaseHolder mDatabaseHolder;
    private static final RulesCombat RULECOMBAT1 = new RulesCombat("name1");

    @Before
    public void setUp() throws Exception {
        mDatabaseHolder = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                DatabaseHolder.class)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build();
        DatabaseManager.clearWholeDatabaseAndAllHolders(mDatabaseHolder);
    }

    @After
    public void tearDown() throws Exception {
        DatabaseManager.clearWholeDatabaseAndAllHolders(mDatabaseHolder);
        DatabaseManager.closeDatabase(mDatabaseHolder);
    }

    @Test
    public void toStringTest() {
        Assert.assertEquals("RulesCombat", RULES_COMBAT.toString());
        Assert.assertNotEquals("RulesSkills", RULES_COMBAT.toString());
        Assert.assertNotEquals("RulesCombat", RULES_SKILLS.toString());
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains("RulesCombat", RulesType.class));
        Assert.assertFalse(contains("RandomText", RulesType.class));
    }

    @Test
    public void getDatabaseListTest() {
        RULECOMBAT1.setItemID(1);
        mDatabaseHolder.RULES_COMBAT_LIST.add(RULECOMBAT1);
        Assert.assertEquals(mDatabaseHolder.RULES_COMBAT_LIST, RULES_COMBAT.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_SKILLS_LIST, RULES_COMBAT.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_COMBAT_LIST, RULES_SKILLS.getDatabaseList(mDatabaseHolder));
    }

    @Test
    public void getDatabaseMapTest() {
        RULECOMBAT1.setItemID(1);
        mDatabaseHolder.RULES_COMBAT_MAP.put(1, RULECOMBAT1);
        Assert.assertEquals(mDatabaseHolder.RULES_COMBAT_MAP, RULES_COMBAT.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_SKILLS_MAP, RULES_COMBAT.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_COMBAT_MAP, RULES_SKILLS.getDatabaseMap(mDatabaseHolder));
    }

    @Test
    public void getNewObjectTest() {
        Assert.assertEquals(com.aurora.d20_35_app.models.constants.RulesCombat.class, RULES_COMBAT.getNewObject().getClass());
        Assert.assertEquals(new RulesCombat(), RULES_COMBAT.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.constants.RulesSkills.class, RULES_COMBAT.getNewObject().getClass());
        Assert.assertNotEquals(new RulesSkills(), RULES_COMBAT.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.constants.RulesCombat.class, RULES_SKILLS.getNewObject().getClass());
        Assert.assertNotEquals(new RulesCombat(), RULES_SKILLS.getNewObject());
    }

    @Test
    public void getDAOTest() {
        Assert.assertEquals(mDatabaseHolder.rulesCombatDAO(), RULES_COMBAT.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.rulesSkillsDAO(), RULES_COMBAT.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.rulesCombatDAO(), RULES_SKILLS.getDAO(mDatabaseHolder));
    }

    @Test
    public void getAllFromDatabaseTest() {
        RULECOMBAT1.setItemID(1);
        mDatabaseHolder.rulesCombatDAO().insert(RULECOMBAT1);
        Assert.assertEquals(RULES_COMBAT.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.rulesCombatDAO().getItems());
        Assert.assertNotEquals(RULES_SKILLS.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.rulesCombatDAO().getItems());
        Assert.assertNotEquals(RULES_COMBAT.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.rulesSkillsDAO().getItems());
    }
}