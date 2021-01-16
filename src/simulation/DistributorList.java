package simulation;

import java.util.List;

/**
 * Clasa contine o lista de distribuitori
 * si operatiile asociate acestora
 * folosite in simulare
 */
public class DistributorList {

    private List<Distributor> distributors;

    /**
     *
     * @return Intoarce lista de distribuitori
     */
    public List<Distributor> getDistributors() {
        return distributors;
    }

    /**
     *
     * Seteaza lista de distribuitori
     */
    public void setDistributors(final List<Distributor> distributors) {
        this.distributors = distributors;
    }

    public DistributorList(final List<Distributor> distributors) {
        this.distributors = distributors;
    }

    /**
     * Gaseste primul distribuitor nefalimentar
     * @return indexul primului distribuitor nefalimentar
     */
    public int firstNonBankrupt() {
        int i = 0;
        // Gasesc primul distribuitor nefalimentar
        while (distributors.get(i).isBankrupt() && i < distributors.size()) {
            i++;
        }
        // Toti distribuitorii sunt falimentari
        if (i >= distributors.size()) {
            return -1;
        }
        return i;
    }

    /**
     * @return distribuitorul cu rata cea mai mica
     */
    public Distributor findMinRate() {

        // Primul distribuitor nefalimentar
        int i = firstNonBankrupt();
        // Daca nu exista distribuitori nefaliti
        if (i == -1) {
            return null;
        }

        int price = distributors.get(i).getContractPrice();
        Distributor minDistributor = distributors.get(i);

        for (Distributor distributor : distributors) {
            // Este inca in joc
            if (!distributor.isBankrupt()) {
                // Fac update la pretul minim al contractului
                if (price > distributor.getContractPrice()) {
                    price = distributor.getContractPrice();
                    minDistributor = distributor;
                }
            }
        }
        return minDistributor;
    }

    /**
     * Platesc distribuitorii costurile dintr-o luna si dau faliment,
     * daca e cazul
     */
    public void payCosts() {
        for (Distributor distributor : distributors) {
            // Fiecare buget al distribuitorului se updateaza
            Integer dif = distributor.getInitialBudget() - distributor.getCost();

            // Daca mai e in joc
            if (!distributor.isBankrupt()) {
                distributor.setInitialBudget(dif);
            }

            // Da faliment
            if (distributor.getInitialBudget() < 0) {
                distributor.setBankrupt(true);
            }
        }
    }

    /**
     * Elimina contractele terminate, luna precedenta
     */
    public void outContract() {
        // Elimin contractele terminate
        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                int i = distributor.getContractList().size() - 1;
                while (i >= 0) {
                    Contract contract = distributor.getContractList().get(i);
                    Integer actualMonths = contract.getRemainedContractMonths();
                    if (actualMonths == 0) {
                        distributor.getContractList().remove(i);
                    }
                    i--;
                }
            }
        }
    }

    public void applyStrategyList(List<Producer> dataBasedProducers) {
        // Aplic strategia pentru toti distribuitorii
        for (Distributor distributor : distributors) {
            distributor.applyStrategy(dataBasedProducers);
        }
    }

    // Schimb costurile de productie pentru noii distribuitori
    public void applyChangesCosts() {
        for (Distributor distributor : distributors) {
            distributor.setProductionCost();
        }
    }

    /**
     *  Toți distribuitorii non-bankrupt își actualizează producătorii dacă e cazul și își calculează costul de producție
     *     ordinea actualizării se face în ordinea crescătoare a id-urilor distribuitorilor
     *     în momentul în care unui distribuitor îi vine randul să își actualizeze producătorii,
     *     întâi se scoate acest distribuitor de la toți producătorii de la care lua energie și
     *     apoi se aplică strategia de alegere.
     * @return
     */
    public void updateProducers(List<Producer> dataBaseProducers) {
        for (Distributor distributor : distributors) {
            // Daca e inca in joc
            if (!distributor.isBankrupt()) {
                // Daca trebuie facut update
                if (distributor.isUpdate()) {

                    // il scot de la toti producatorii de la care lua energie
                    for (Producer producer : distributor.getProducerList()) {
                        producer.removeDistributor(distributor.getId());
                    }
                    // actualizez strategia
                    applyStrategyList(dataBaseProducers);

                    // actualizez costul de productie
                    applyChangesCosts();

                    // L-am update-at
                    distributor.setUpdate(false);
                }
            }
        }
    }
    @Override
    public String toString() {
        return "DistributorList{" +
                "distributors=" + distributors +
                '}';
    }
}
