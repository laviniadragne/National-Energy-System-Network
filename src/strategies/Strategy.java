package strategies;

import simulation.Producer;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa abstractă comună pentru strategiile pe care le-am
 * implementat
 * Ea contine si cateva metode de sortare ale producatorilor,
 * folosite de mai multe strategii, in parte
 */
public abstract class Strategy {

    /**
     * Pe baza numelui unei strategii, returnez o lista cu
     * posibilii producatori ce respecta acea strategie
     * @param databasedProducers baza de date cu producatori
     * @param energyNeededKW cantitatea de energie necesara
     * @return o lista cu toti producatorii sortati dupa o
     * anumita strategie
     */
    public abstract List<Producer> chooseProducers(List<Producer> databasedProducers,
                                          Integer energyNeededKW);

    /**
     * Iterez prin lista sortata de producatori si-i aleg pe cei
     * care imi dau cata energie are nevoie distribuitorul
     * @param energyNeededKW cantitatea de energie necesara
     * @param myProducers o lista sortata de producatori din care trebuie
     *                    alesi doar unii, pentru a asigura cantitatea
     *                    necesara de energie
     * @return lista cu producatorii necesari pentru a indeplini
     *         acea cantitate de energie
     */
    public List<Producer> choiceSortedProducers(Integer energyNeededKW,
                                                List<Producer> myProducers) {
        int currentEnergy = 0;
        int i = 0;
        List<Producer> returnProducers = new ArrayList<>();

        // parcurg lista de producatori si abonez distribuitori
        while ((currentEnergy < energyNeededKW) && (i < myProducers.size())) {

            // Daca mai primeste distribuitori
            if (!myProducers.get(i).getMaxDistributors().equals(myProducers.get(i)
                                        .getActualDistributors())) {

                // Actualizez cantitatea de energie curenta a distribuitorului
                currentEnergy += myProducers.get(i).getEnergyPerDistributor();

                // Actualizez numarul maxim de distribuitori
                // Pe care mai poate sa-i primeasca un producator
                Integer actualDistributors = myProducers.get(i).getActualDistributors();
                actualDistributors++;
                myProducers.get(i).setActualDistributors(actualDistributors);

                // Adaug in lista de returnat
                returnProducers.add(myProducers.get(i));

            }
            i++;
        }
        // Returnez lista de producatori din strategie pentru acel distribuitor
        return returnProducers;
    }

    /**
     * Sortare dupa pret, cantitatea de energie, id
     * @param producers lista de producatori ce poate fi
     *                  sortata
     */
    public void sortGreenStrategy(List<Producer> producers) {
        producers.sort((p1, p2) -> {
            if (!p1.getPriceKW().equals(p2.getPriceKW())) {
                return Double.compare(p1.getPriceKW(), p2.getPriceKW());
            } else {
                if (!p1.getEnergyPerDistributor().equals(p2.getEnergyPerDistributor())) {
                    return p2.getEnergyPerDistributor() - p1.getEnergyPerDistributor();
                }
                return p1.getId() - p2.getId();
            }
        });
    }

    /**
     * Sortare dupa cantitatea de energie, id
     * @param producers lista de producatori ce poate fi sortata
     */
    public void sortQuantityStrategy(List<Producer> producers) {
        producers.sort((p1, p2) -> {
            if (!p1.getEnergyPerDistributor().equals(p2.getEnergyPerDistributor())) {
                return p2.getEnergyPerDistributor() - p1.getEnergyPerDistributor();
            }
            return p1.getId() - p2.getId();
        });
    }
}
