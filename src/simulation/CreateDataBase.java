package simulation;

import input.ConsumerInputData;
import input.Input;
import input.MonthlyUpdates;
import utils.Constants;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Clasa contine metode statice folosite pentru a crea baza de date
 * de clase cu care se va lucra pe parcursul simularii
 */
public final class CreateDataBase {

    private CreateDataBase() {
    }

    /**
     * Propria mea lista de consumatori, de la runda 0
     *
     * @param input clasa cu datele parsate
     * @return o lista de consumatori
     */
    public static List<Consumer> createConsumers(final Input input) {
        EntityFactory myFactory = EntityFactory.getInstance();
        List<Consumer> consumers = new LinkedList<>();
        Consumer newConsumer;
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            newConsumer = (Consumer) myFactory.createEntity(Constants.CONSUMER,
                    input.getInitialData().getConsumers().get(i));
            consumers.add(newConsumer);
        }
        return consumers;
    }

    /**
     * Propria mea lista de distributori, de la runda 0
     *
     * @param input clasa cu datele parsate
     * @return o lista de distribuitori
     */
    public static List<Distributor> createDistributors(final Input input) {
        EntityFactory myFactory = EntityFactory.getInstance();
        List<Distributor> distributors = new LinkedList<>();
        Distributor newDistributor;
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            newDistributor = (Distributor) myFactory.createEntity(Constants.DISTRIBUTOR,
                    input.getInitialData().getDistributors().get(i));
            distributors.add(newDistributor);
        }
        return distributors;
    }

    /**
     * Creeaza un hashmap cu key-ul id-ul distribuitorului si value obiectul in sine
     *
     * @param distributors lista pe baza careia se creeaza map-ul
     * @return un map cu key-ul id-ul di valoarea distribuitorul
     */
    public static Map<Integer, Distributor> createMapDis(final List<Distributor> distributors) {
        Map<Integer, Distributor> distributorMap = new HashMap<>();
        for (Distributor distributor : distributors) {
            distributorMap.put(distributor.getId(), distributor);
        }
        return distributorMap;
    }

    /**
     * Propria mea lista de producatori, de la runda 0
     *
     * @param input clasa cu datele parsate
     * @return o lista de producatori
     */
    public static List<Producer> createProducers(final Input input) {
        EntityFactory myFactory = EntityFactory.getInstance();
        List<Producer> producers = new LinkedList<>();
        Producer newProducer;
        for (int i = 0; i < input.getInitialData().getProducers().size(); i++) {
            newProducer = (Producer) myFactory.createEntity(Constants.PRODUCER,
                    input.getInitialData().getProducers().get(i));
            producers.add(newProducer);
        }
        return producers;
    }

    /**
     * Creeaza un hashmap cu key-ul id-ul producatorului si value obiectul in sine
     *
     * @param producers lista pe baza careia se creeaza map-ul
     * @return un map cu key-ul id-ul si valoarea producatorului
     */
    public static Map<Integer, Producer> createMapProd(final List<Producer> producers) {
        Map<Integer, Producer> producerMap = new HashMap<>();
        for (Producer producer : producers) {
            producerMap.put(producer.getId(), producer);
        }
        return producerMap;
    }

    /**
     * Adauga noii consumatori din luna respectiva
     *
     * @param consumers      lista actuala de consumatori
     * @param monthlyUpdates luna in care se fac update-uri
     */
    public static void updateConsumers(final List<Consumer> consumers,
                                       final MonthlyUpdates monthlyUpdates) {
        for (ConsumerInputData addConsumer : monthlyUpdates.getNewConsumers()) {
            EntityFactory myFactory = EntityFactory.getInstance();
            Consumer newConsumer;
            newConsumer = (Consumer) myFactory.createEntity(Constants.CONSUMER,
                    addConsumer);
            consumers.add(newConsumer);
        }
    }


    /**
     * Se fac update-uri la producatori
     * @param actualMonth update-urile din luna respectiva
     * @param producerMap Hashmap cu key-ul id-ul producatorului
     * si valoarea producatorului in sine
     */
    public static void updateProducers(final MonthlyUpdates actualMonth,
                                   final Map<Integer, Producer> producerMap) {
        for (int i = 0; i < actualMonth.getProducerChanges().size(); i++) {
            // Ii gasesc id-ul producatorului la care trebuie facut update
            Integer idUpdate = actualMonth.getProducerChanges().get(i).getId();
            Integer energyPerDistributor = actualMonth.getProducerChanges().get(i).getEnergyPerDistributor();

            // Il caut in dictionar
            Producer updateProducer = producerMap.get(idUpdate);

            // Fac update la energia ce o poate oferi un producator si notific distribuitorii
            updateProducer.updateMonths(energyPerDistributor);
            }
    }

    /**
     * Se fac update-uri la costurile de infrastructura ale distribuitorilor
     * @param actualMonth update-urile din luna respectiva
     * @param distributorMap Hashmap cu key-ul id-ul distribuitorului
     *                       si valoarea distribuitorul in sine
     */
    public static void updateDistributors(final MonthlyUpdates actualMonth,
                                   final Map<Integer, Distributor> distributorMap) {
        for (int i = 0; i < actualMonth.getDistributorChanges().size(); i++) {
            // Ii gasesc id-ul distribuitorului la care trebuie facut update
            Integer idUpdate = actualMonth.getDistributorChanges().get(i).getId();
            Integer infrastructCost = actualMonth.getDistributorChanges().get(i).getInfrastructureCost();

            // Il caut in dictionar
            Distributor updateDistribuitor = distributorMap.get(idUpdate);

            // Daca mai e in joc, ii updatez costurile
            if (!updateDistribuitor.isBankrupt()) {
                updateDistribuitor.setInitialInfrastructureCost(infrastructCost);
            }
        }
    }
}
