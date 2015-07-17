package rev.model.properties;

/**
 * An entity with a population.
 * 
 *
 * 
 */
public interface Populated {

    /**
     * Returns the population of the entity.
     * 
     * @return the population.
     */
    public Integer getPopulation();

    /**
     * Provides population to the entity.
     * 
     * @param population
     *            the number of people to provide.
     * @return the number of people provided.
     */
    public Integer addPopulation(Integer population);

    /**
     * Takes population from the entity.
     * 
     * @param population
     *            the number of people to take.
     * @return the number of people taken.
     */
    public Integer removePopulation(Integer population);
}