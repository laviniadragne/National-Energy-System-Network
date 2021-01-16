package simulation;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Producer extends Observable implements Entity {

        private Integer id;
        private EnergyType energyType;
        private Integer maxDistributors;
        private Double priceKW;
        private Integer energyPerDistributor;
        private Integer actualDistributors;
        private List<Distributor> distributors;
        private List<MonthlyStats> monthlyStats;

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

        public Integer getActualDistributors() {
            return actualDistributors;
        }

        public void setActualDistributors(Integer actualDistributors) {
            this.actualDistributors = actualDistributors;
        }

        public List<Distributor> getDistributors() {
            return distributors;
        }

        public void setDistributors(List<Distributor> distributors) {
            this.distributors = distributors;
        }

        public List<MonthlyStats> getMonthlyStats() {
            return monthlyStats;
        }

        public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
            this.monthlyStats = monthlyStats;
        }

        public Producer(Integer id, EnergyType energyType,
                        Integer maxDistributors,
                        Double priceKW, Integer energyPerDistributor) {
                        this.id = id;
                        this.energyType = energyType;
                        this.maxDistributors = maxDistributors;
                        this.priceKW = priceKW;
                        this.energyPerDistributor = energyPerDistributor;
                        this.actualDistributors = 0;
                        this.distributors = new ArrayList<>();
                        this.monthlyStats = new ArrayList<>();
            }

        // Setez noile date si notific observatorii
        // Golesc listele de distribuitori ale unui producator
        public void updateMonths(Integer energyPerDistributor) {
            this.setEnergyPerDistributor(energyPerDistributor);
            setChanged();
            notifyObservers();

            //distributors.clear();
        }

        // Scoate un distribuitor din lista
        public void removeDistributor(Integer id) {
            // Caut distribuitorul
            int ok = 0;
            int i;
            for (i = 0; i < distributors.size(); i++) {
                if (distributors.get(i).getId().equals(id)) {
                    ok = 1;
                    break;
                }
            }

            // daca l-am gasit
            if (ok == 1) {
                distributors.remove(i);
            }

        }

        @Override
        public String toString() {
            return "Producer{" +
                    "id=" + id +
//                    ", energyType=" + energyType +
//                    ", maxDistributors=" + maxDistributors +
//                    ", priceKW=" + priceKW +
//                    ", energyPerDistributor=" + energyPerDistributor +
//                    ", actualDistributors=" + actualDistributors +
//                    ", distributors=" + distributors +
                    '}';
        }
}
