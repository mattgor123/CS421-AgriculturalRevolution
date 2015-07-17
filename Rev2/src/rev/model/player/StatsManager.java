package rev.model.player;

/**
 * Manages player stats.
 * 
 *
 * 
 */
public interface StatsManager {

    public static final int MAX_RESEARCH = 1000000;
    
    /**
     * Returns the population of the player's colony.
     * 
     * @return the player population.
     */
    public Integer getPopulation();

    /**
     * Returns the amount of research the player has accomplished.
     * 
     * @return the amount of research.
     */
    public Integer getResearch();

    /**
     * Adds research to the player's stats.
     * 
     * @param amount the amount of research to add.
     */
    public void addResearch(Integer amount);    
}