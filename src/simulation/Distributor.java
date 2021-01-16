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
    private List<Contract> contractList;
    private boolean isBankrupt;
    private Integer energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private List<Producer> producerList;
    private boolean update;

    public Integer getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(Integer energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        this.producerList = producerList;
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
     * @return Intoarce costul de productie a distribuitorului
     */
    public Integer getInitialProductionCost() {
        return initialProductionCost;
    }


    /**
     *
     * @return Intoarce lista de contracte ale distribuitorului
     */
    public List<Contract> getContractList() {
        return contractList;
    }

    /**
     *
     * Seteaza lista de contracte ale distribuitorului
     */
    public void setContractList(final List<Contract> contractList) {
        this.contractList = contractList;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
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
        this.initialProductionCost =  (int) Math.round(Math.floor(cost / 10));
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
        if (producerStrategy.label.equals("GREEN")) {
            GreenStrategy greenStrategy = new GreenStrategy();
            newProducers = greenStrategy.chooseProducers(databasedProducers, energyNeededKW);
        }
        else {
            if (producerStrategy.label.equals("QUANTITY")) {
                QuantityStrategy quantityStrategy = new QuantityStrategy();
                newProducers = quantityStrategy.chooseProducers(databasedProducers, energyNeededKW);
            }
            else {
                if (producerStrategy.label.equals("PRICE")) {
                    PriceStrategy priceStrategy = new PriceStrategy();
                    newProducers = priceStrategy.chooseProducers(databasedProducers, energyNeededKW);
                }
            }
        }
        // Curat lista de producatori de luna trecuta
        producerList.clear();

        // Actualizez lista cu noii producatori
        producerList = newProducers;

        // Pentru fiecare producator adaug distribuitorul corespunzator
        // Si il adaug in lista de observatori
        for (Producer producer : newProducers) {
            producer.getDistributors().add(this);
            producer.addObserver(this);
        }
    }

    @Override
    public String toString() {
        return "Distributor{" +
                "id=" + id +
                ", contractLength=" + contractLength +
                ", initialBudget=" + initialBudget +
                ", initialInfrastructureCost=" + initialInfrastructureCost +
                ", initialProductionCost=" + initialProductionCost +
                ", contractList=" + contractList +
                ", isBankrupt=" + isBankrupt +
                ", energyNeededKW=" + energyNeededKW +
                ", producerStrategy=" + producerStrategy +
                ", update=" + update +
                '}';
    }

    // Retin ca tre sa fac update luna viitoare
        @Override
        public void update(Observable o, Object arg) {
            this.update = true;
        }
}
