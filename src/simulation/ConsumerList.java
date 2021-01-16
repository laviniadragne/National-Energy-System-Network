package simulation;

import java.util.List;

/**
 * Clasa contine informatiile despre lista de consumatori
 * folosita in simularea jocului
 */
public class ConsumerList {

    private List<Consumer> consumers;

    /**
     *
     * @return Intoarce lista de consumatori
     */
    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     *
     * Seteaza lista de consumatori
     */
    public void setConsumers(final List<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ConsumerList(final List<Consumer> consumers) {
        this.consumers = consumers;
    }

    /**
     * Scade numarul de luni din contract
     */
    public void decreaseMonth() {
        for (Consumer consumer : consumers) {
            // Daca mai e in joc
            if (!consumer.isBankrupt()) {
               consumer.updateMonths();
            }
        }
    }

    /**
     * Adauga contracte consumatorilor, daca nu au deja
     * @param distributor distribuitorul la care se aboneaza
     * @param price pretul contractului
     */
    public void updateContracts(final Distributor distributor,
                                final Integer price) {
        for (Consumer consumer : consumers) {
            // Nu are distribuitor/ i-a dat faliment distribuitorul sau i-a expirat contractul
            if (consumer.getContract().getDistributorId() == -1
                    || consumer.getContract().getRemainedContractMonths() == 0
                    && (!consumer.isBankrupt())) {

                Contract newContract = new Contract(consumer.getId(), price,
                        distributor.getContractLength());
                newContract.setDistributorId(distributor.getId());
                // Adaug distribuitorului noul contract
                distributor.getContractList().add(newContract);
                // Adaug consumatorului contractul
                consumer.setContract(newContract);

            }
        }
    }

    /**
     * Adauga salariile lunare consumatorilor
     */
    public void addSalary() {
        for (Consumer consumer : consumers) {
            if (!consumer.isBankrupt()) {
                consumer.updateSalary();

            }
        }
    }

    /**
     * Caut in toate contractele consumatorilor si sterg distribuitorul,
     * setandu-i id-ul cu -1
     * @param distributor distribuitorul ce trebuie eliminat
     */
    public void deleteDistributor(final Distributor distributor) {
        for (Consumer consumer : this.consumers) {
            if (!consumer.isBankrupt()) {
                if (consumer.getContract().getDistributorId().equals(distributor.getId())) {
                    consumer.getContract().setDistributorId(-1);
                }
            }
        }
    }
}
