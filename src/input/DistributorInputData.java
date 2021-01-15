package input;


import strategies.EnergyChoiceStrategyType;

/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui distribuitor
 */

public class DistributorInputData {

    private Integer id;
    private Integer contractLength;
    private Integer initialBudget;
    private Integer initialInfrastructureCost;
    private Integer energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;

    public Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    /**
     *
     * @return Intoarce bugetul unui distribuitor
     */
    public Integer getInitialBudget() {
        return initialBudget;
    }

    /**
     *
     * Seteaza bugetul unui distribuitor
     */
    public void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     *
     * @return Intoarce id-ul unui distribuitor
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * Seteaza id-ul unui distribuitor
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     *
     * @return Intoarce lungimea contractului unui distribuitor
     */
    public Integer getContractLength() {
        return contractLength;
    }

    /**
     *
     * Seteaza lungimea contractului unui distribuitor
     */
    public void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }

    /**
     *
     * @return Intoarce costul infrastructurii unui distribuitor
     */
    public Integer getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     *
     * Seteaza costul infrastructurii unui distribuitor
     */
    public void setInitialInfrastructureCost(final Integer initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    @Override
    public String toString() {
        return "DistributorInputData{" +
                "id=" + id +
                ", contractLength=" + contractLength +
                ", initialBudget=" + initialBudget +
                ", initialInfrastructureCost=" + initialInfrastructureCost +
                ", energyNeededKW=" + energyNeededKW +
                ", producerStrategy=" + producerStrategy +
                '}';
    }
}
