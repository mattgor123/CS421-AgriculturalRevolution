package rev.model.ordnance;

import rev.model.core.ModelDynamic;
import rev.model.properties.Positioned;

/**
 * An ordnance effect.
 * 
 *
 * 
 */
public interface OrdnanceEffect extends ModelDynamic, Positioned {

    /**
     * The maximum potency of an ordnance effect.
     * 
     */
    public static final Integer MAXIMUM_POTENCY = 100;

    /**
     * Returns the potency of the explosive effect.
     * 
     * @return the potency.
     */
    public Integer getPotency();

    /**
     * Returns the probability that the ordnance effect will reproduce itself.
     * 
     * @return the probability of replication.
     */
    public Double getReplicationProbability();
}