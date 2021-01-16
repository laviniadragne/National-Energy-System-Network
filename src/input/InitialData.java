package input;

import java.util.List;


/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unei baze initiale de date
 */

public class InitialData {
    private List<ConsumerInputData> consumers;
    private List<DistributorInputData> distributors;
    private List<ProducerInputData> producers;

    /**
     *
     * @return Intoarce o lista de consumatori
     */
    public List<ConsumerInputData> getConsumers() {
        return consumers;
    }

    /**
     *
     * Seteaza o lista de consumatori
     */
    public void setConsumers(final List<ConsumerInputData> consumers) {
        this.consumers = consumers;
    }

    /**
     *
     * @return Intoarce o lista de distribuitori
     */
    public List<DistributorInputData> getDistributors() {
        return distributors;
    }

    /**
     *
     * Seteaza o lista de consumatori
     */
    public void setDistributors(final List<DistributorInputData> distributors) {
        this.distributors = distributors;
    }

    public final List<ProducerInputData> getProducers() {
        return producers;
    }

    public final void setProducers(List<ProducerInputData> producers) {
        this.producers = producers;
    }
}
