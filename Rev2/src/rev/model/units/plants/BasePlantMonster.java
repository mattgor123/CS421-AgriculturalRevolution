package rev.model.units.plants;

import rev.model.core.ModelDelegate;
import rev.model.ordnance.CobaltOrdnanceEffect;
import rev.model.ordnance.ExplosiveOrdnanceEffect;
import rev.model.units.core.BaseAbstractUnit;
import rev.model.units.core.BaseUnitsManager;
import rev.utility.RandomUtility;

/**
 * A plant monster implements as an apse actor.
 * 
 *
 * 
 */
public class BasePlantMonster extends BaseAbstractUnit implements PlantMonster {

    /**
     * The health of the monster.
     * 
     */
    private int health = 100;

    /**
     * Initializes the plant monster.
     * 
     * @param x
     *            the x position of the monster.
     * @param y
     *            the y position of the monster.
     * @param health
     *            the initial health of the monster.
     */
    public BasePlantMonster(Integer x, Integer y, int health,
            BaseUnitsManager unitsManager) {
        super(x, y, new PlantMonsterBehaviors.PlantMonsterFindCity(),
                unitsManager);
        this.health = health;
    }

    @Override
    public void customUpdate(ModelDelegate model) {
        if (RandomUtility.nextBoolean(0.005)) {
            this.setBehavior(new PlantMonsterBehaviors.PlantMonsterFindCity());
        }
        if (this.health < 0) {
            this.setFlag(true);
        }
    }

    /**
     * The apse actor type.
     * 
     */
    public static final String ACTOR_TYPE = "plant_monster";

    @Override
    public void respondToEffect(CobaltOrdnanceEffect effect) {
        this.setFlag(true);
    }

    @Override
    public Integer getHealth() {
        return this.health;
    }

    @Override
    public String getType() {
        return ACTOR_TYPE;
    }

    @Override
    public Integer getSpeed() {
        return 5;
    }

    @Override
    public void respondToEffect(ExplosiveOrdnanceEffect effect) {
        this.health -= effect.getPotency();
    }
}