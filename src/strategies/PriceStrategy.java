package strategies;

import simulation.Producer;
import simulation.ProducerList;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategia conform careia un distribuitor își alege producătorii
 * prioritizând doar după preț, apoi după cantitate
 */
public class PriceStrategy extends Strategy {

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
        // date, pentru a nu strica lista din baza de date
        List<Producer> myProducers = new ArrayList<>(databasedProducers);

        // Asignez lista la un obiect de tip lista
        ProducerList producerList = new ProducerList(myProducers);

        // Sortez corespunzator
        sortGreenStrategy(producerList.getProducers());

        // Returnez doar cati producatori am nevoie
        return choiceSortedProducers(energyNeededKW, producerList.getProducers());
    }
}
