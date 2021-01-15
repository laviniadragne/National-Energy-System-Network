package simulation;

import input.Input;
import input.MonthlyUpdates;
import output.*;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Clasa efectueaza simularea descrisa in enunt
 * a sistemului energetic, pe baza datelor primite
 * si a unor metode statice
 */
public final class Simulation {

    private Simulation() {

    }

    /**
    Scoate din joc distribuitori si sterge contractele cu acestia din liste
    consumatorilor
     */
    public static void updateDistributors(final DistributorList distributorList,
                                          final ConsumerList consumersList) {
        for (Distributor distributor : distributorList.getDistributors()) {
            Integer buget = distributor.getInitialBudget();
            if (buget < 0) {
                // Eliminat din joc
                distributor.setBankrupt(true);
                // Caut in toate contractele consumatorilor si sterg distribuitorul,
                consumersList.deleteDistributor(distributor);
            }
        }
    }

    /**
    Plata lunara a ratei de catre toti consumatorii
    Daca nu pot sa plateasca si sunt deja restanti, sunt scosi din joc
    si din lista de contracte a distribuitorului
     */
    public static void payRate(final ConsumerList consumers,
                               final Map<Integer, Distributor> distributorMap) {
        for (Consumer consumer : consumers.getConsumers()) {
            // Daca mai e in joc
            if (!consumer.isBankrupt()) {
                int dif = consumer.getInitialBudget() - consumer.getContract().getPrice();

                Integer idDistribuitor = consumer.getContract().getDistributorId();
                // Gasesc distribuitorul la care are contractul

                Distributor distributor = distributorMap.get(idDistribuitor);

                // Nu este restant
                if (consumer.getIdOverdue() == -1) {
                    // Poate plati rata luna asta
                    if (dif >= 0) {
                        consumer.setInitialBudget(dif);
                        distributor.updatePaidRate(consumer);
                        // Nu poate plati luna asta
                    } else {
                        consumer.setIdOverdue(idDistribuitor);
                        consumer.setPriceOverdue(consumer.getContract().getPrice());
                    }
                // Este restant
                } else {
                    Integer mustPay = (int) Math.round(Math.floor(Constants.OVERDUE_FACTOR
                            * consumer.getPriceOverdue())) + consumer.getContract().getPrice();

                    // Cui e restant
                    Distributor lastDistributor = distributorMap.get(consumer.getIdOverdue());

                    // Daca nu e afara din joc
                    if ((!lastDistributor.isBankrupt())) {
                        // Poate plati
                        if (consumer.getInitialBudget() - mustPay >= 0) {
                            // Se updateaza buget-ul
                            consumer.setInitialBudget(consumer.getInitialBudget() - mustPay);

                            // Plateste catre fostul distribuitor
                            lastDistributor.remainingPayment(consumer);

                            // Plateste si catre noul distribuitor
                            distributor.updatePaidRate(consumer);
                            // Nu poate plati
                        } else {
                            // E scos din joc
                            consumer.setBankrupt(true);
                        }
                        // Distribuitorul catre care trebuia sa plateasca, luna trecuta,
                        // a dat faliment
                    } else {
                        // Daca poate plati suma
                        if (consumer.getInitialBudget()
                                - consumer.getContract().getPrice() >= 0) {
                            consumer.setInitialBudget(consumer.getInitialBudget()
                                    - consumer.getContract().getPrice());

                            // Ii actualizez bugetul distribuitorului
                            distributor.updatePaidRate(consumer);
                        } else {
                            // E scos din joc
                            consumer.setBankrupt(true);
                        }
                    }
                }
            }
        }
    }

    /**
     * Elimin contractele consumatorilor falimentari
     * @param consumers lista de consumatori
     * @param distributors lista de distribuitori
     */
    public static void failedContract(final List<Consumer> consumers,
                                      final List<Distributor> distributors) {
        for (Consumer consumer : consumers) {
            // Daca e falimentar
            if (consumer.isBankrupt()) {
                // Il scot din lista de contracte a distribuitorului
                for (Distributor distributor : distributors) {
                    distributor.removeConsumer(consumer);
                }
            }
        }
    }

    /**
     * Creeaza o clasa de Output pentru a scrie rezultatele, pe baza a 2 liste
     * de consumatori si distribuitori
     * @param consumers lista de consumatori
     * @param distributors lista de distribuitori
     * @return o clasa de output
     */
    public static Output createOutput(final List<Consumer> consumers,
                                      final List<Distributor> distributors,
                                      final List<Producer> producers) {
        // Scriu rezultatele
        Output output = new Output();
        for (Consumer consumer : consumers) {
            ConsumerOutputData consumerOutputData = new ConsumerOutputData(consumer.getId(),
                    consumer.isBankrupt(), consumer.getInitialBudget());
            output.getConsumers().add(consumerOutputData);
        }

        for (Distributor distributor : distributors) {
            // O lista de contracte de out
            List<ContractOutputData> outContracts = new ArrayList<>();

            for (Contract contract : distributor.getContractList()) {
                ContractOutputData outContract = new ContractOutputData(contract.getConsumerId(),
                        contract.getPrice(), contract.getRemainedContractMonths());
                outContracts.add(outContract);
            }

            DistributorOutputData distributorOutputData =
                    new DistributorOutputData(distributor.getId(), distributor.getEnergyNeededKW(),
                                    distributor.getContractPrice(), distributor.getInitialBudget(),
                                distributor.getProducerStrategy(),distributor.isBankrupt(), outContracts);

            output.getDistributors().add(distributorOutputData);
        }

        for (Producer producer : producers) {
            // Lista de distribuitori in fiecare luna
            List<MonthlyStatsOuputData> monthlyStatsOuputDataList = new ArrayList<>();
            for (MonthlyStats monthlyStats : producer.getMonthlyStats()) {
                MonthlyStatsOuputData monthlyStatsOuputData
                        = new MonthlyStatsOuputData(monthlyStats.getMonth(),
                            monthlyStats.getDistributorsIds());
                monthlyStatsOuputDataList.add(monthlyStatsOuputData);
            }
            ProducerOutputData producerOutputData = new ProducerOutputData(producer.getId(),
                    producer.getMaxDistributors(), producer.getPriceKW(), producer.getEnergyType(),
                    producer.getEnergyPerDistributor(), monthlyStatsOuputDataList);

            output.getEnergyProducers().add(producerOutputData);
        }

        return output;
    }


    /**
     * Ordinea de efectuare a operatiilor dintr-o luna
     * @param consumers clasa cu o lista de consumatori
     * @param distributors clasa cu o lista de distribuitori
     */
    public static void operationsStart(final ConsumerList consumers,
                                  final DistributorList distributors,
                                  final Map<Integer, Distributor> distributorMap) {
        // Gasesc distribuitorul cu rata minima
        Distributor subscribeDistribuitor = distributors.findMinRate();
        Integer price = subscribeDistribuitor.getContractPrice();

        // Se fac update-urile la salarii
        consumers.addSalary();

        // Elimin contractele incheiate
        distributors.outContract();

        // Abonez consumatorii la el
        consumers.updateContracts(subscribeDistribuitor, price);

        // Platesc ratele si costurile de productie
        payRate(consumers, distributorMap);
        distributors.payCosts();

        // Scad numarul de luni, din contractele consumatorilor
        consumers.decreaseMonth();

        // Elimin contractele pt consumatorii falimentari
        failedContract(consumers.getConsumers(), distributors.getDistributors());

        // Scot din joc distribuitorii
        updateDistributors(distributors, consumers);
    }

    /**
     * Simularea jocului
     * @param input clasa de input cu datele parsate primite
     * @return output o clasa folosita pentru a scrie datele
     * in format JSON
     */
    public static Output getSimulation(final Input input) {

        // Runda 0
        int turns = 0;
        // Se creeaza baza de date
        // Se încarcă datele de intrare despre distribuitori, consumatori și producători
        List<Consumer> consumers = CreateDataBase.createConsumers(input);
        List<Distributor> distributors = CreateDataBase.createDistributors(input);
        List<Producer> producers = CreateDataBase.createProducers(input);

        Map<Integer, Distributor> distributorMap = CreateDataBase.createMapDis(distributors);
        Map<Integer, Producer> producerMap = CreateDataBase.createMapProd(producers);

        ConsumerList consumerList = new ConsumerList(consumers);
        DistributorList distributorList = new DistributorList(distributors);
        ProducerList producerList = new ProducerList(producers);


        // Aplic strategia ca sa aleg producatorii
        // Distribuitorii isi aleg producatorii
        distributorList.applyStrategyList(producerList.getProducers());

        // Aplic pattern-ul Observatory
        producerList.addObservators();

        // Aplic schimbul de costuri de productie
        distributorList.applyChangesCosts();


//
//        // Aplic strategia
//        distributorList.applyStrategyList(producerList.getProducers());
//
//        // Aplic schimbul de costuri de productie
//        distributorList.applyChangesCosts();
//
//        System.out.println("Lista cu distribuitorii si producatorii fiecaruia ");
//        for (Distributor distributor : distributorList.getDistributors()) {
//            System.out.println(distributor.getId() + ": ");
//            System.out.print(distributor.getProducerList());
//        }
//
//        System.out.println("Lista cu toti producatorii: ");
//        for (Producer producer : producerList.getProducers()) {
//            System.out.println(producer.toString());
//        }




        // Se apeleaza ordinea operatiilor de la inceput de luna
        operationsStart(consumerList, distributorList, distributorMap);

        while (turns < input.getNumberOfTurns()) {

            // Se adauga noii consumatori
            MonthlyUpdates actualMonth = input.getMonthlyUpdates().get(turns);
            CreateDataBase.updateConsumers(consumers, actualMonth);

            // Se adauga noile update-uri la costurile distribuitorilor
            CreateDataBase.updateDistributors(actualMonth, distributorMap);


            // Se apeleaza operatiile de la inceputul lunii
            operationsStart(consumerList, distributorList, distributorMap);

            // Operatiile din timpul lunii
            // Se face update la Producatori
            CreateDataBase.updateProducers(actualMonth, producerMap);

            // Operatiile de la finalul lunii
            distributorList.updateProducers(producers);

            turns++;
            // Fac update la listele de distribuitori din acea luna
            // ale fiecarui producator
            producerList.updateMonthlyStats(turns);

        }

        return createOutput(consumers, distributors, producers);
    }
}
