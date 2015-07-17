package rev.model.transport;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;

/**
 * A transport mission.
 * 
 *
 * 
 */
public interface TransportMission {

    /**
     * Returns the origin of the mission.
     * 
     * @return
     */
    public Settlement getOrigin();

    /**
     * Returns the destination of the mission.
     * 
     * @return
     */
    public Settlement getDestination();

    /**
     * Executes the mission by modifying the destination settlement.
     * 
     * @param model
     *            the model.
     */
    public void execute(ModelDelegate model);
}