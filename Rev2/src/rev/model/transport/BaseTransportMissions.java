package rev.model.transport;

import rev.model.core.ModelDelegate;
import rev.model.settlement.core.Settlement;

/**
 * The set of transport missions.
 * 
 *
 * 
 */
public class BaseTransportMissions {

    public static abstract class BaseAbstractTransportMission implements
            TransportMission {

        private final Settlement origin;

        private final Settlement destination;

        public BaseAbstractTransportMission(Settlement origin,
                Settlement destination) {
            this.origin = origin;
            this.destination = destination;
        }

        @Override
        public Settlement getOrigin() {
            return this.origin;
        }

        @Override
        public Settlement getDestination() {
            return this.destination;
        }
    }

    public static class BaseDefaultTransportMission extends
            BaseAbstractTransportMission {

        public BaseDefaultTransportMission(Settlement origin,
                Settlement destination) {
            super(origin, destination);
        }

        @Override
        public void execute(ModelDelegate model) {
            System.out.println("default mission between " + this.getOrigin()
                    + " and " + this.getDestination() + " executed");
        }
    }
}