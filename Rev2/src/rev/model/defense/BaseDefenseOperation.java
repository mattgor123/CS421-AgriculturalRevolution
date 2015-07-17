package rev.model.defense;

import rev.model.core.ModelDelegate;
import rev.model.ordnance.BaseCobaltOrdnanceEffect;
import rev.model.ordnance.BaseExplosiveOrdnanceEffect;
import rev.model.ordnance.BaseShell;
import rev.model.ordnance.Shell;
import rev.model.settlement.core.Settlement;
import rev.utility.Coordinate;

/**
 * An implementation of the defense operation interface that inserts new
 * ordnance into the apse engine.
 * 
 *
 * 
 */
public class BaseDefenseOperation implements DefenseOperation {

    /**
     * The settlement.
     * 
     */
    private final Settlement settlement;

    public BaseDefenseOperation(Settlement settlement) {
        this.settlement = settlement;
    }

    @Override
    public Settlement getSettlement() {
        return this.settlement;
    }

    @Override
    public Boolean fireCobaltShell(Coordinate<Double> target,
            ModelDelegate model) {
        if (!this.canAfford(DefenseOperation.COBALT_SHELL_COST)) {
            return false;
        }
        Coordinate<Double> origin = new Coordinate<Double>((double) this
                .getSettlement().getX() + 0.5, (double) this.getSettlement()
                .getY() + 0.5);
        Shell shell = new BaseShell(origin, target, 0.1,
                new BaseCobaltOrdnanceEffect(target.getX().intValue(),
                        (int) target.getY().intValue(), 100));
        model.getOrdnanceManager().createShell(shell);
        this.getSettlement().getStorageOperation()
                .withdrawQuantity(COBALT_SHELL_COST);
        return true;
    }

    @Override
    public Boolean fireExplosiveShell(Coordinate<Double> target,
            ModelDelegate model) {
        Coordinate<Double> origin = new Coordinate<Double>((double) this
                .getSettlement().getX() + 0.5, (double) this.getSettlement()
                .getY() + 0.5);
        Shell shell = new BaseShell(origin, target, 0.1,
                new BaseExplosiveOrdnanceEffect(target.getX().intValue(),
                        (int) target.getY().intValue(), 100));
        model.getOrdnanceManager().createShell(shell);
        return true;
    }

    private Boolean canAfford(Integer amount) {
        if (this.settlement.getStorageOperation().getQuantity() >= amount) {
            return true;
        }
        return false;
    }
}