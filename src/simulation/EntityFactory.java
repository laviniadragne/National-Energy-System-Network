package simulation;

import input.ConsumerInputData;
import input.DistributorInputData;
import input.ProducerInputData;
import utils.Constants;

/**
 * Clasa contine design pattern-ul factory
 * si design pattern-ul singleton, putand
 * fi creat un singur obiect de acest tip.
 * Ea este folosita pentru a crea entitati
 * de tip consumator sau distribuitor.
 */
public final class EntityFactory {

    private static EntityFactory instance = null;

    private EntityFactory() {

    }

    /**
     * Aplicat design pattern-ul Singleton
     * @return Intoarce instanta clasei factory
     */
    public static EntityFactory getInstance() {
        if (instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

    /**
     * Creeaza pe baza unui type dat, ca si parametru,
     * o entitate
     * @param entityType tipul de entitate pe care trebuie sa-l creeze
     * @param entity informatia pe care trebuie sa o
     *               atribuie entitatii create
     * @return intoarce un obiect de tip distribuitor sau consumator
     * sau o exceptie in cazul in care i se cere sa creeze un tip de entitate
     * inca necunoscut
     */
    public Entity createEntity(final String entityType,
                               final Object entity) {
        if (entityType.equals(Constants.CONSUMER)) {
                return new Consumer(((ConsumerInputData) entity).getId(),
                        ((ConsumerInputData) entity).getInitialBudget(),
                        ((ConsumerInputData) entity).getMonthlyIncome());
        }
        if (entityType.equals(Constants.DISTRIBUTOR)) {
                return new Distributor(((DistributorInputData) entity).getId(),
                        ((DistributorInputData) entity).getContractLength(),
                        ((DistributorInputData) entity).getInitialBudget(),
                        ((DistributorInputData) entity).getInitialInfrastructureCost(),
                        ((DistributorInputData) entity).getEnergyNeededKW(),
                        ((DistributorInputData) entity).getProducerStrategy());

//                        ((DistributorInputData) entity).getInitialProductionCost());
        }
        if (entityType.equals(Constants.PRODUCER)) {
            return new Producer(((ProducerInputData) entity).getId(),
                    ((ProducerInputData) entity).getEnergyType(),
                    ((ProducerInputData) entity).getMaxDistributors(),
                    ((ProducerInputData) entity).getPriceKW(),
                    ((ProducerInputData) entity).getEnergyPerDistributor());
        }
        throw new IllegalArgumentException("The entity type " + entityType + " is not recognized.");
    }
}
