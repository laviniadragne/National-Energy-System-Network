package input;

import entities.EnergyType;

/**
 * Clasa contine informatiile parsate din testele de input
 * corespunzatoare unui producator
 */
public class ProducerInputData {
    private Integer id;
    private EnergyType energyType;
    private Integer maxDistributors;
    private Double priceKW;
    private Integer energyPerDistributor;

    public final Integer getId() {
        return id;
    }

    public final void setId(Integer id) {
        this.id = id;
    }

    public final EnergyType getEnergyType() {
        return energyType;
    }

    public final void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public final Integer getMaxDistributors() {
        return maxDistributors;
    }

    public final void setMaxDistributors(Integer maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public final Double getPriceKW() {
        return priceKW;
    }

    public final void setPriceKW(Double priceKW) {
        this.priceKW = priceKW;
    }

    public final Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public final void setEnergyPerDistributor(Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
