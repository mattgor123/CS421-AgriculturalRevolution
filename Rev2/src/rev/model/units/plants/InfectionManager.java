package rev.model.units.plants;

import java.util.List;

import rev.model.data.TileOccupiedException;

/**
 * An infection manager. Manages spores and plant monsters.
 * 
 *
 * 
 */
public interface InfectionManager {

    /**
     * Places a spore on the map.
     * 
     * @param x
     *            the x position of the spore.
     * @param y
     *            the y position of the spore.
     * @param potency
     *            the potency of the spore.
     */
    public void placeSpore(Integer x, Integer y, Integer potency);

    /**
     * Places a plant monster on the map.
     * 
     * @param x
     *            the x position of the monster.
     * @param y
     *            the y position of the monster.
     * @param health
     *            the health of the monster.
     */
    public void placePlantMonster(Integer x, Integer y, Integer health)
            throws TileOccupiedException;

    /**
     * Returns the set of plant monsters on the map.
     * 
     * @return the set of plant monsters.
     */
    public List<PlantMonster> getPlantMonsters();
}