package strategies;

import simulation.Producer;
import simulation.ProducerList;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategia conform careia un distribuitor își alege
 * producătorii prioritizându-i pe cei cu renewable energy întâi,
 * apoi după preț, apoi după cantitate
 */
public class GreenStrategy extends Strategy {

    /**
     * Alege pe baza unei cantitati de energie primite
     * producatorii ce se incadreaza in acea strategie
     * @param databasedProducers baza de date cu producatori
     * @param energyNeededKW cantitatea de energie necesara
     * @return lista de producatori alesi
     */
    @Override
    public List<Producer> chooseProducers(List<Producer> databasedProducers,
                                          Integer energyNeededKW) {

        // Sortez o lista noua de producatori ce contine lista din baza de
        // date
        List<Producer> myProducers;

        List<Producer> renewableProducers = new ArrayList<>();
        List<Producer> nonrenewableProducers = new ArrayList<>();

        // Sortez pe liste de distribuitori cu energie regenerabila si neregenerabila
        for (Producer producer : databasedProducers) {
            if (producer.getEnergyType().isRenewable()) {
                renewableProducers.add(producer);
            } else {
                nonrenewableProducers.add(producer);
            }
        }

        ProducerList producerRenewableList = new ProducerList(renewableProducers);
        ProducerList producerNonrenewableList = new ProducerList(nonrenewableProducers);


        // Sortez corespunzator fiecare lista
        sortGreenStrategy(producerRenewableList.getProducers());
        sortGreenStrategy(producerNonrenewableList.getProducers());

        // Lista myProducers va contine la inceput producatorii cu energie regenerabila
        // si apoi pe cei cu energie neregenerabila
        myProducers = producerRenewableList.getProducers();
        myProducers.addAll(producerNonrenewableList.getProducers());

        // Aleg din lista sortata doar cati producatori am nevoie
        // pentru am furniza cantitatea de energie dorita
        return choiceSortedProducers(energyNeededKW, myProducers);
    }
}
