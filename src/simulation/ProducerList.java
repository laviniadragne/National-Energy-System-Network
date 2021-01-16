package simulation;

import java.util.List;

/**
 * Clasa contine o lista de producatori
 * si operatiile asociate acestora
 * folosite in simulare
 */
public class ProducerList {
    private List<Producer> producers;

    public final List<Producer> getProducers() {
        return producers;
    }

    public final void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    public ProducerList(List<Producer> producers) {
        this.producers = producers;
    }


    /**
     * Adauga listele cu distribuitorii abonati la producatori,
     * din luna respectiva
     * @param monthId id-ul lunii curente
     */
    public void updateMonthlyStats(Integer monthId) {
        for (Producer producer : producers) {
            MonthlyStats newMonth = new MonthlyStats(monthId);

            // Adaug in lista din luna respectiva toti distribuitorii
            // abonati la acel producator
            for (Distributor distributor : producer.getDistributors()) {
                if (!newMonth.getDistributorsIds().contains(distributor.getId())) {
                    newMonth.getDistributorsIds().add(distributor.getId());
                }
            }
                producer.getMonthlyStats().add(newMonth);
        }
    }

    /**
     * Sterge un distribuitor primit ca si parametru
     * din listele producatorilor si din listele de observatori
     * @param distributor ce trebuie sters
     */
    public void deleteDistributor(Distributor distributor) {
        for (Producer producer : producers) {

            // Il caut in listele de distribuitori ale fiecarui producator si il sterg
            producer.removeDistributor(distributor.getId());

            // Era prezent in lista, il sterg si din listele de observatori
            producer.deleteObserver(distributor);
        }
    }
}
