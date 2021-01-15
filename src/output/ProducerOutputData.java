package output;

import entities.EnergyType;

import java.util.List;

public class ProducerOutputData {
    private Integer id;
    private Integer maxDistributors;
    private Double priceKW;
    private EnergyType energyType;
    private Integer energyPerDistributor;
    private List<MonthlyStatsOuputData> monthlyStats;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public Integer getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(Integer maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public Double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(Double priceKW) {
        this.priceKW = priceKW;
    }

    public Integer getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(Integer energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStatsOuputData> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStatsOuputData> monthlyStats) {
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
