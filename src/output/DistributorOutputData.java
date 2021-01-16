package output;

import strategies.EnergyChoiceStrategyType;

import java.util.List;

/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare unui distribuitor
 */
public class DistributorOutputData {

    private Integer id;
    private Integer energyNeededKW;
    private Integer contractCost;
    private Integer budget;
    private EnergyChoiceStrategyType producerStrategy;
    private final boolean isBankrupt;
    private List<ContractOutputData> contracts;

    /**
     *
     * @return Intoarce id-ul unui distribuitor
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @return Seteaza id-ul unui distribuitor
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Intoarce bugetul unui distribuitor
     */
    public Integer getBudget() {
        return budget;
    }

    /**
     *
     * Seteaza bugetul unui distribuitor
     */
    public void setBudget(final Integer budget) {
        this.budget = budget;
    }

    /**
     *
     * @return Intoarce starea unui distribuitor
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     *
     * @return Intoarce lista de contracte ale unui distribuitor
     */
    public List<ContractOutputData> getContracts() {
        return contracts;
    }

    /**
     *
     * Seteaza lista de contracte ale unui distribuitor
     */
    public void setContracts(final List<ContractOutputData> contracts) {
        this.contracts = contracts;
    }

    public final Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final Integer getContractCost() {
        return contractCost;
    }

    public final void setContractCost(Integer contractCost) {
        this.contractCost = contractCost;
    }

    public final EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public DistributorOutputData(Integer id, Integer energyNeededKW, Integer contractCost,
                                 Integer budget, EnergyChoiceStrategyType producerStrategy,
                                 boolean isBankrupt, List<ContractOutputData> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }
}
