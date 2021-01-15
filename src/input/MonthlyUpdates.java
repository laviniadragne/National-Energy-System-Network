package input;

import java.util.List;

/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui update lunar
 */

public class MonthlyUpdates {

    private List<ConsumerInputData> newConsumers;
    private List<CostInputData> distributorChanges;
    private List<ProducerCostInputData> producerChanges;

    /**
     *
     * @return Intoarce o lista de noi consumatori
     */
    public List<ConsumerInputData> getNewConsumers() {
        return newConsumers;
    }

    /**
     *
     * Seteaza o lista de noi consumatori
     */
    public void setNewConsumers(final List<ConsumerInputData> newConsumers) {
        this.newConsumers = newConsumers;
    }


    public List<CostInputData> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<CostInputData> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ProducerCostInputData> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerCostInputData> producerChanges) {
        this.producerChanges = producerChanges;
    }

    @Override
    public String toString() {
        return '{' +
                "newConsumers=" + newConsumers +
                ", distributorChanges=" + distributorChanges +
                ", producerChanges=" + producerChanges +
                '}';
    }
}
