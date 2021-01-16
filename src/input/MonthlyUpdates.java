package input;

import java.util.List;

/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui update lunar
 */

public class MonthlyUpdates {

    private List<ConsumerInputData> newConsumers;
    private List<CostInputData> distributorChanges;
    private List<ProducerUpdateInputData> producerChanges;

    public final List<ConsumerInputData> getNewConsumers() {
        return newConsumers;
    }

    public final void setNewConsumers(final List<ConsumerInputData> newConsumers) {
        this.newConsumers = newConsumers;
    }


    public final List<CostInputData> getDistributorChanges() {
        return distributorChanges;
    }

    public final void setDistributorChanges(List<CostInputData> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public final List<ProducerUpdateInputData> getProducerChanges() {
        return producerChanges;
    }

    public final void setProducerChanges(List<ProducerUpdateInputData> producerChanges) {
        this.producerChanges = producerChanges;
    }

}
