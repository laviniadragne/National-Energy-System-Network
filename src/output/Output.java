package output;


import java.util.ArrayList;
import java.util.List;

/**
 * Clasa contine informatiile ce trebuie scrise in output
 */
public class Output {

    private List<ConsumerOutputData> consumers;
    private List<DistributorOutputData> distributors;
    private List<ProducerOutputData> energyProducers;

    public final List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final List<ConsumerOutputData> consumers) {
        this.consumers = consumers;
    }

    public final List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final List<DistributorOutputData> distributors) {
        this.distributors = distributors;
    }

    public final List<ProducerOutputData> getEnergyProducers() {
        return energyProducers;
    }

    public final void setEnergyProducers(List<ProducerOutputData> energyProducers) {
        this.energyProducers = energyProducers;
    }

    public Output() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
        this.energyProducers = new ArrayList<>();
    }
}
