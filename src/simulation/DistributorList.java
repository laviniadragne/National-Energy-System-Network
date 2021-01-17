package simulation;

import java.util.List;

/**
 * Clasa contine o lista de distribuitori
 * si operatiile asociate acestora
 * folosite in simulare
 */
public class DistributorList {

    private List<Distributor> distributors;

    public final List<Distributor> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final List<Distributor> distributors) {
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
        while (distributors.get(i).isBankrupt()) {
            i++;
        }
        // Toti distribuitorii sunt falimentari
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

    /**
     * Aplica strategia pentru fiecare distribuitor in parte
     * daca a fost notificat
     * @param dataBasedProducers lista de producatori
     *                           folosita pentru a se aplica
     *                           strategiile
     */
    public void applyStrategyList(List<Producer> dataBasedProducers) {
        // Aplic strategia pentru toti distribuitorii
        for (Distributor distributor : distributors) {
            // A fost notificat
            if (distributor.isUpdate()) {
                distributor.applyStrategy(dataBasedProducers);
            }
        }
    }

    /**
     * Schimb costurile de productie pentru noii distribuitori
     */
    public void applyChangesCosts() {
        for (Distributor distributor : distributors) {
            distributor.setProductionCost();
        }
    }

    /**
     *  Toți distribuitorii non-bankrupt își actualizează producătorii dacă e cazul și î
     *  și calculează costul de producție ordinea actualizării se face în ordinea crescătoare
     *  a id-urilor distribuitorilor în momentul în care unui distribuitor îi vine randul să
     *  își actualizeze producătorii, întâi se scoate acest distribuitor de la toți
     *  producătorii de la care lua energie și apoi se aplică strategia de alegere.
     */
    public void chooseProducers(List<Producer> dataBaseProducers) {
        for (Distributor distributor : distributors) {
            // Daca e inca in joc
            if (!distributor.isBankrupt()) {
                // Daca trebuie facut update
                if (distributor.isUpdate()) {

                    // Il scot de la toti producatorii de la care lua energie
                    for (Producer producer : distributor.getProducerList()) {
                        producer.removeDistributor(distributor.getId());
                        producer.deleteObserver(distributor);

                        // Micsorez numarul de distribuitori actuali ai unui producator
                        Integer actualDistributors =  producer.getActualDistributors();
                        actualDistributors--;
                        producer.setActualDistributors(actualDistributors);
                    }

                    // Actualizez strategia
                    distributor.applyStrategy(dataBaseProducers);

                    // L-am update-at
                    distributor.setUpdate(false);
                }
            }
        }
    }

    /**
     * Seteaza intr-un camp al distribuitorilor noile preturi
     * de contracte
     */
    public void setContractPriceList() {
        for (Distributor distributor : distributors) {
            distributor.setVarContractPrice(distributor.getContractPrice());
        }
    }
}
