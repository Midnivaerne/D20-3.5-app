package com.aurora.d20_35_app.models.typeHelpers;

import com.aurora.d20_35_app.models.constants.RulesCombat;
import com.aurora.d20_35_app.models.constants.RulesSkills;
import com.aurora.d20_35_app.database.DatabaseHolder;
import com.aurora.d20_35_app.database.DatabaseManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import static com.aurora.d20_35_app.models.typeHelpers.RulesType.RulesCombat;
import static com.aurora.d20_35_app.models.typeHelpers.RulesType.RulesSkills;
import static com.aurora.d20_35_app.models.typeHelpers.RulesType.contains;

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
        Assert.assertEquals("RulesCombat", RulesCombat.toString());
        Assert.assertNotEquals("RulesSkills", RulesCombat.toString());
        Assert.assertNotEquals("RulesCombat", RulesSkills.toString());
    }

    @Test
    public void containsTest() {
        Assert.assertTrue(contains("RulesCombat"));
        Assert.assertFalse(contains("RandomText"));
    }

    @Test
    public void getDatabaseListTest() {
        RULECOMBAT1.setItemID(1);
        mDatabaseHolder.RULES_COMBAT_LIST.add(RULECOMBAT1);
        Assert.assertEquals(mDatabaseHolder.RULES_COMBAT_LIST, RulesCombat.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_SKILLS_LIST, RulesCombat.getDatabaseList(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_COMBAT_LIST, RulesSkills.getDatabaseList(mDatabaseHolder));
    }

    @Test
    public void getDatabaseMapTest() {
        RULECOMBAT1.setItemID(1);
        mDatabaseHolder.RULES_COMBAT_MAP.put(1, RULECOMBAT1);
        Assert.assertEquals(mDatabaseHolder.RULES_COMBAT_MAP, RulesCombat.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_SKILLS_MAP, RulesCombat.getDatabaseMap(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.RULES_COMBAT_MAP, RulesSkills.getDatabaseMap(mDatabaseHolder));
    }

    @Test
    public void getNewObjectTest() {
        Assert.assertEquals(com.aurora.d20_35_app.models.constants.RulesCombat.class, RulesCombat.getNewObject().getClass());
        Assert.assertEquals(new RulesCombat(), RulesCombat.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.constants.RulesSkills.class, RulesCombat.getNewObject().getClass());
        Assert.assertNotEquals(new RulesSkills(), RulesCombat.getNewObject());

        Assert.assertNotEquals(com.aurora.d20_35_app.models.constants.RulesCombat.class, RulesSkills.getNewObject().getClass());
        Assert.assertNotEquals(new RulesCombat(), RulesSkills.getNewObject());
    }

    @Test
    public void getDAOTest() {
        Assert.assertEquals(mDatabaseHolder.rulesCombatDAO(), RulesCombat.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.rulesSkillsDAO(), RulesCombat.getDAO(mDatabaseHolder));
        Assert.assertNotEquals(mDatabaseHolder.rulesCombatDAO(), RulesSkills.getDAO(mDatabaseHolder));
    }

    @Test
    public void getAllFromDatabaseTest() {
        RULECOMBAT1.setItemID(1);
        mDatabaseHolder.rulesCombatDAO().insert(RULECOMBAT1);
        Assert.assertEquals(RulesCombat.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.rulesCombatDAO().getItems());
        Assert.assertNotEquals(RulesSkills.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.rulesCombatDAO().getItems());
        Assert.assertNotEquals(RulesCombat.getAllFromDatabase(mDatabaseHolder), mDatabaseHolder.rulesSkillsDAO().getItems());
    }
}