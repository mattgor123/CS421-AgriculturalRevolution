package rev.model.player;

import rev.model.core.BaseModelDelegate;
import rev.model.settlement.core.Settlement;

public class BaseStatsManager implements StatsManager {

    private BaseModelDelegate delegate;
    
    private int researchProgress = 0;

    public BaseStatsManager(BaseModelDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public Integer getPopulation() {
        int aggregate = 0;
        for (Settlement settlement : this.delegate.getSettlementManager()
                .getSettlements()) {
            aggregate += settlement.getPopulation();
        }
        return aggregate;
    }

    @Override
    public Integer getResearch() {
        return this.researchProgress;
    }

    @Override
    public void addResearch(Integer amount) {
        this.researchProgress += amount;
    }

}