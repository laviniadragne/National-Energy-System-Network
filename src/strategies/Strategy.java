package strategies;

import simulation.Producer;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy {

    /**
     * Pe baza numelui unei strategii, returnez o lista cu
     * posibilii producatori ce respecta acea strategie
     * @param
     * @return
     */
    public abstract List<Producer> chooseProducers(List<Producer> databasedProducers,
                                          Integer energyNeededKW);

    /**
     * Iterez prin lista sortata de producatori si-i aleg pe cei
     * care imi dau cata energie are nevoie distribuitorul
     * @param energyNeededKW
     * @param myProducers
     * @return
     */
    public List<Producer> choiceSortedProducers(Integer energyNeededKW, List<Producer> myProducers) {
        int currentEnergy = 0;
        int i = 0;
        List<Producer> returnProducers = new ArrayList<>();

        // parcurg lista de producatori si abonez distribuitori
        while ((currentEnergy < energyNeededKW) && (i < myProducers.size())) {

            // Daca mai primeste distribuitori
            if (!myProducers.get(i).getMaxDistributors().equals(myProducers.get(i).getActualDistributors())) {

                // Actualizez cantitatea de energie curenta a distribuitorului
                currentEnergy += myProducers.get(i).getEnergyPerDistributor();

                // Actualizez numarul maxim de distribuitori
                // Pe care mai poate sa-i primeasca un producator
                Integer actualDistributors = myProducers.get(i).getActualDistributors();
                actualDistributors++;
                myProducers.get(i).setActualDistributors(actualDistributors);

                // Adaug in lista de returnat
                returnProducers.add(myProducers.get(i));

                i++;
            }
        }
        // Returnez lista de producatori din strategie pentru acel distribuitor
        return returnProducers;
    }
}
