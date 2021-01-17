package simulation;

/**
 * Interfata contine campurile comune
 * pentru distribuitori si consumatori
 */
public interface Entity {
    Integer id = null;
    Integer initialBudget = null;
    boolean isBankrupt = false;

    /**
     * @return Intoarce id-ul entitatii
     */
    default Integer getId() {
        return id;
    }

    /**
     * @return Intoarce bugetul entitatii
     */
    default Integer getInitialBudget() {
        return initialBudget;
    }

    /**
     * @return Intoarce starea entitatii
     */
    default boolean isBankrupt() {
        return isBankrupt;
    }
}
