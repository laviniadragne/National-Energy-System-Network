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

    public final List<ConsumerInputData> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final List<ConsumerInputData> consumers) {
        this.consumers = consumers;
    }

    public final List<DistributorInputData> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final List<DistributorInputData> distributors) {
        this.distributors = distributors;
    }

    public final List<ProducerInputData> getProducers() {
        return producers;
    }

    public final void setProducers(List<ProducerInputData> producers) {
        this.producers = producers;
    }
}
