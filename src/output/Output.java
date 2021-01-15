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

    /**
     *
     * @return Intoarce o lista de consumatori pentru a fi scrise
     * in fisierele de output
     */
    public List<ConsumerOutputData> getConsumers() {
        return consumers;
    }

    /**
     *
     * Seteaza o lista de consumatori pentru a fi scrise
     * in fisierele de output
     */
    public void setConsumers(final List<ConsumerOutputData> consumers) {
        this.consumers = consumers;
    }

    /**
     *
     * @return Intoarce o lista de distribuitori pentru a fi scrise
     * in fisierele de output
     */
    public List<DistributorOutputData> getDistributors() {
        return distributors;
    }

    /**
     *
     * Seteaza o lista de distribuitori pentru a fi scrise
     * in fisierele de output
     */
    public void setDistributors(final List<DistributorOutputData> distributors) {
        this.distributors = distributors;
    }

    public List<ProducerOutputData> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(List<ProducerOutputData> energyProducers) {
        this.energyProducers = energyProducers;
    }

    public Output() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
        this.energyProducers = new ArrayList<>();
    }
}
