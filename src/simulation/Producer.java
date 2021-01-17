package simulation;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Clasa contine informatiile despre un producator
 * folosit in simularea jocului
 * Ea va fi, in cadrul simularii, un observabil
 * pentru distribuitori.
 */
public class Producer extends Observable implements Entity {

        private Integer id;
        private EnergyType energyType;
        private Integer maxDistributors;
        private Double priceKW;
        private Integer energyPerDistributor;
        private Integer actualDistributors;
        private List<Distributor> distributors;
        private List<MonthlyStats> monthlyStats;

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

        public final Integer getActualDistributors() {
            return actualDistributors;
        }

        public final void setActualDistributors(Integer actualDistributors) {
            this.actualDistributors = actualDistributors;
        }

        public final List<Distributor> getDistributors() {
            return distributors;
        }

        public final void setDistributors(List<Distributor> distributors) {
            this.distributors = distributors;
        }

        public final List<MonthlyStats> getMonthlyStats() {
            return monthlyStats;
        }

        public final void setMonthlyStats(List<MonthlyStats> monthlyStats) {
            this.monthlyStats = monthlyStats;
        }

        public Producer(final Integer id, final EnergyType energyType,
                        final Integer maxDistributors,
                        final Double priceKW, final Integer energyPerDistributor) {
                        this.id = id;
                        this.energyType = energyType;
                        this.maxDistributors = maxDistributors;
                        this.priceKW = priceKW;
                        this.energyPerDistributor = energyPerDistributor;
                        this.actualDistributors = 0;
                        this.distributors = new ArrayList<>();
                        this.monthlyStats = new ArrayList<>();
            }

    /**
     * Setez noile date referitoare la energie si notific observatorii
     * ca producatorului lor i s-au facut update-uri
     * @param newEnergyPerDistributor valoare energiei noi,
     *                             din update-ul lunar
     */
    public void updateMonths(Integer newEnergyPerDistributor) {
            this.setEnergyPerDistributor(newEnergyPerDistributor);
            setChanged();
            notifyObservers();
        }

    /**
     * Scoate un distribuitor din lista unui producator
     * @param idDistributor -ul distribuitorului
     */
    public void removeDistributor(Integer idDistributor) {
            // Caut distribuitorul in lista
            int ok = 0;
            int i;
            for (i = 0; i < distributors.size(); i++) {
                if (distributors.get(i).getId().equals(idDistributor)) {
                    ok = 1;
                    break;
                }
            }

            // Daca l-am gasit, il sterg
            if (ok == 1) {
                distributors.remove(i);
            }

        }

}
