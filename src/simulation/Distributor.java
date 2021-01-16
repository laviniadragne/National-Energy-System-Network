package simulation;

import strategies.EnergyChoiceStrategyType;
import strategies.GreenStrategy;
import strategies.PriceStrategy;
import strategies.QuantityStrategy;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Clasa contine datele unui distribuitor
 * folosite in simulare
 */
public class Distributor implements Entity, Observer {

    private Integer id;
    private Integer contractLength;
    private Integer initialBudget;
    private Integer initialInfrastructureCost;
    private Integer initialProductionCost;
    private final List<Contract> contractList;
    private boolean isBankrupt;
    private Integer energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private List<Producer> producerList;
    private boolean update;
    private Integer contractPrice;

    public final Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public final void setEnergyNeededKW(Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public final EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public final void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public final List<Producer> getProducerList() {
        return producerList;
    }

    /**
     *
     * @return Intoarce starea distribuitorului
     */
    @Override
    public boolean isBankrupt() {
        return isBankrupt;
    }


    /**
     *
     * Seteaza starea distribuitorului
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }


    /**
     *
     * @return Intoarce id-ul distribuitorului
     */
    @Override
    public Integer getId() {
        return id;
    }


    /**
     *
     * Seteaza id-ul distribuitorului
     */
    public void setId(final Integer id) {
        this.id = id;
    }


    /**
     *
     * @return Intoarce bugetul initial distribuitorului
     */
    @Override
    public Integer getInitialBudget() {
        return initialBudget;
    }


    /**
     *
     * Seteaza bugetul initial distribuitorului
     */
    public void setInitialBudget(final Integer initialBudget) {
        this.initialBudget = initialBudget;
    }


    /**
     *
     * @return Intoarce lungimea contractului distribuitorului
     */
    public Integer getContractLength() {
        return contractLength;
    }

    /**
     *
     * Seteaza lungimea contractului distribuitorului
     */
    public void setContractLength(final Integer contractLength) {
        this.contractLength = contractLength;
    }

    /**
     *
     * @return Intoarce costul infrastructurii distribuitorului
     */
    public Integer getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     *
     * Seteaza costul infrastructurii distribuitorului
     */
    public void setInitialInfrastructureCost(final Integer initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }


    /**
     *
     * @return Intoarce lista de contracte ale distribuitorului
     */
    public List<Contract> getContractList() {
        return contractList;
    }

    public final boolean isUpdate() {
        return update;
    }

    public final void setUpdate(boolean update) {
        this.update = update;
    }

    public final Integer getVarContractPrice() {
        return contractPrice;
    }

    public final void setVarContractPrice(Integer newContractPrice) {
        this.contractPrice = newContractPrice;
    }

    public Distributor(final Integer id, final Integer contractLength,
                       final Integer initialBudget,
                       final Integer initialInfrastructureCost,
                       final Integer energyNeededKW,
                       final EnergyChoiceStrategyType producerStrategy) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = 0;
        this.contractList = new ArrayList<>();
        this.isBankrupt = false;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
        this.producerList = new ArrayList<>();
        this.update = true;
    }


    /**
     *
     * @return Profitul din acea luna
     */
    public Integer getProfit() {
        int profit;
        profit = (int) Math.round(Math.floor(Constants.PROFIT_FACTOR * initialProductionCost));
        return profit;
    }

    /**
     *
     * @return Pretul contractului oferit de acel distribuitor
     */
    public Integer getContractPrice() {
        // Cazul de baza
        Integer price = getProfit() + initialProductionCost;

        // Are consumatori
        if (contractList.size() != 0) {
            price += (int) Math.round(Math.floor((initialInfrastructureCost
                    * Constants.NEUTRAL_FACTOR) / this.contractList.size()));

            // Nu are consumatori
        } else {
            price += initialInfrastructureCost;
        }

        return price;
    }

    /**
     *
     * @return Costul din acea luna
     */
    public Integer getCost() {
        int cost;
        cost = initialInfrastructureCost + initialProductionCost * contractList.size();
        return cost;
    }

    /**
     * Calculeaza costurile de productie din fiecare luna
     */
    public void setProductionCost() {
        double cost = 0.0;
        for (Producer producer : producerList) {
            cost += producer.getEnergyPerDistributor()
                            * producer.getPriceKW();
        }
        this.initialProductionCost =
                (int) Math.round(Math.floor(cost / Constants.SCALING_FACTOR));
    }

    /**
     * Elimina consumatorul dat ca parametru din lista de contracte
     */
    public void removeConsumer(final Consumer consumer) {
        int i = getContractList().size() - 1;
        while (i >= 0) {
            Contract contract = getContractList().get(i);
            // Sterg contractul din lista
            if (contract.getConsumerId().equals(consumer.getId())) {
                getContractList().remove(i);
            }
            i--;
        }
    }

    /**
     * Adaug pretul unui contract platit la bugetul distribuitorului
     * Consumatorul primit ca si parametru este platitorul
     */
    public void updatePaidRate(final Consumer consumer) {
        // Ii actualizez bugetul distribuitorului
        Integer actualBuget = getInitialBudget();
        actualBuget += consumer.getContract().getPrice();
        setInitialBudget(actualBuget);
    }

    /**
     * Ii este platita rata de luna trecuta
     * @param consumer este cel care plateste rata
     */
    public void remainingPayment(final Consumer consumer) {
        Integer lastBuget = getInitialBudget();
        lastBuget += (int) Math.round(Math.floor(Constants.OVERDUE_FACTOR
                                               * consumer.getPriceOverdue()));
        setInitialBudget(lastBuget);
    }


    /**
     * Metoda aplica o strategie lunar pe baza de date a producatorilor
     * In urma metodei distribuitorul va avea asignata o noua lista
     * de producatori
     * De asemenea, fiecare distribuitor este adaugat in lista de
     * observatori a noului producator
     */
    public void applyStrategy(List<Producer> databasedProducers) {

        List<Producer> newProducers = new ArrayList<>();
        if (producerStrategy.label.equals(Constants.GREEN)) {
            GreenStrategy greenStrategy = new GreenStrategy();
            newProducers = greenStrategy.chooseProducers(databasedProducers,
                                                        energyNeededKW);
        } else {
            if (producerStrategy.label.equals(Constants.QUANTITY)) {
                QuantityStrategy quantityStrategy = new QuantityStrategy();
                newProducers = quantityStrategy.chooseProducers(databasedProducers,
                                                                energyNeededKW);
            } else {
                if (producerStrategy.label.equals(Constants.PRICE)) {
                    PriceStrategy priceStrategy = new PriceStrategy();
                    newProducers = priceStrategy.chooseProducers(databasedProducers,
                                                                energyNeededKW);
                }
            }
        }
        // Curat lista de producatori de luna trecuta
        producerList.clear();

        // Actualizez lista cu noii producatori
        producerList = newProducers;

        // Am aplicat deja strategia, deci nu mai trebuie facut update
        this.setUpdate(false);

        // Pentru fiecare producator adaug distribuitorul corespunzator
        // Il adaug si in lista de observatori
        for (Producer producer : newProducers) {
            producer.getDistributors().add(this);
            producer.addObserver(this);
        }
    }


    /**
     * Retin ca tre sa fac update luna viitoare
     * distribuitorului
     */
        @Override
        public void update(Observable o, Object arg) {
            this.update = true;
        }
}
