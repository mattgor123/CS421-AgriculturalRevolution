package rev.model.attributes;

import rev.utility.FiniteQuantity;

/**
 * A population attribute.
 * 
 *
 * 
 */
public class Population {

    /**
     * The population size.
     * 
     */
    private FiniteQuantity population;

    /**
     * Initializes the population attribute.
     * 
     * @param initialPopulation
     *            the initial population size.
     */
    public Population(int initialPopulation) {
        this.population = new FiniteQuantity(initialPopulation);
    }

    /**
     * Returns the population size.
     * 
     * @return the population size.
     */
    public int getPopulation() {
        return this.population.getQuantity();
    }

    /**
     * Sets the population of the entity.
     * 
     * @param population
     *            the population of the entity.
     */
    public void setPopulation(int population) {
        this.population = new FiniteQuantity(population);
    }

    /**
     * Adds to the population.
     * 
     * @param population
     *            the population to add.
     */
    public void addPopulation(int population) {
        this.population.depositAmount(population);
    }

    /**
     * Removes from the population.
     * 
     * @param population
     *            the population to remove
     * @return the removed population.
     */
    public int removePopulation(int population) {
        return this.population.withdrawAmount(population);
    }
}