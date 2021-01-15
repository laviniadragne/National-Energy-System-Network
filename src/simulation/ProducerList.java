package simulation;

import java.util.List;

public class ProducerList {
    private List<Producer> producers;

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public ProducerList(List<Producer> producers) {
        this.producers = producers;
    }

    public void sortGreenStrategy() {
        producers.sort((p1, p2) -> {
            if (!p1.getPriceKW().equals(p2.getPriceKW())) {
                return Double.compare(p1.getPriceKW(), p2.getPriceKW());
            } else {
                if (!p1.getEnergyPerDistributor().equals(p2.getEnergyPerDistributor())) {
                    return p2.getEnergyPerDistributor() - p1.getEnergyPerDistributor();
                }
                return p1.getId() - p2.getId();
            }
        });
    }

    public void sortQuantityStrategy() {
        producers.sort((p1, p2) -> {
            if (!p1.getEnergyPerDistributor().equals(p2.getEnergyPerDistributor())) {
                return p2.getEnergyPerDistributor() - p1.getEnergyPerDistributor();
            }
            return p1.getId() - p2.getId();
        });
    }

    public void addObservators() {
        for (Producer producer : producers) {
            for (Distributor distributor : producer.getDistributors()) {
                producer.addObserver(distributor);
            }
        }
    }

    public void updateMonthlyStats(Integer monthId) {
        for (Producer producer : producers) {
            MonthlyStats newMonth = new MonthlyStats(monthId);
            // Adaug in lista din luna respectiva toti distribuitorii
            // abonat la acel producator
            for (Distributor distributor : producer.getDistributors()) {
                newMonth.getDistributorsIds().add(distributor.getId());
            }
            producer.getMonthlyStats().add(newMonth);
        }

    }
}
