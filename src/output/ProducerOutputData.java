package output;

import entities.EnergyType;

import java.util.List;

/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare unui producator
 */
public class ProducerOutputData {
    private Integer id;
    private Integer maxDistributors;
    private Double priceKW;
    private EnergyType energyType;
    private Integer energyPerDistributor;
    private List<MonthlyStatsOuputData> monthlyStats;

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

    public final List<MonthlyStatsOuputData> getMonthlyStats() {
        return monthlyStats;
    }

    public final void setMonthlyStats(List<MonthlyStatsOuputData> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public ProducerOutputData(Integer id, Integer maxDistributors,
                              Double priceKW, EnergyType energyType,
                              Integer energyPerDistributor,
                              List<MonthlyStatsOuputData> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }
}
