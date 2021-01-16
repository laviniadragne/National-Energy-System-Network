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


    public void updateMonthlyStats(Integer monthId) {
        for (Producer producer : producers) {
            MonthlyStats newMonth = new MonthlyStats(monthId);

            // Adaug in lista din luna respectiva toti distribuitorii
            // abonat la acel producator
            for (Distributor distributor : producer.getDistributors()) {
                if (!newMonth.getDistributorsIds().contains(distributor.getId())) {
                    newMonth.getDistributorsIds().add(distributor.getId());
                }
            }
                producer.getMonthlyStats().add(newMonth);
        }
    }

    public void deleteDistributor(Distributor distributor) {
        for (Producer producer : producers) {
            int i;
            int ok = 0;
            // Caut in lista de distribuitori a fiecarui producator
            // pe respectivul, dupa id
            for (i = 0; i < producer.getDistributors().size(); i++) {
                if (distributor.getId().equals(producer.getDistributors().get(i).getId())) {
                    ok = 1;
                    break;
                }
            }
            // Era prezent in lista, il sterg si din listele de observatori
            if (ok == 1) {
                producer.getDistributors().remove(i);
                producer.deleteObserver(distributor);
            }
        }
    }
}
