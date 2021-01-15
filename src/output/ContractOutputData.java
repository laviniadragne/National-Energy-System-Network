package output;

/**
 * Clasa contine informatiile ce trebuie scrise in output
 * corespunzatoare unui contract
 */
public class ContractOutputData {

    private Integer consumerId;
    private Integer price;
    private Integer remainedContractMonths;

    /**
     *
     * @return Intoarce id-ul unui consumator
     */
    public Integer getConsumerId() {
        return consumerId;
    }

    /**
     *
     * Seteaza id-ul unui consumator
     */
    public void setConsumerId(final Integer consumerId) {
        this.consumerId = consumerId;
    }

    /**
     *
     * @return Intoarce pretul unui contract
     */
    public Integer getPrice() {
        return price;
    }

    /**
     *
     * Seteaza pretul unui contract
     */
    public void setPrice(final Integer price) {
        this.price = price;
    }

    /**
     *
     * @return Intoarce numarul de luni ramase din contract
     */
    public Integer getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     *
     * Seteaza numarul de luni ramase din contract
     */
    public void setRemainedContractMonths(final Integer remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public ContractOutputData(final Integer consumerId, final Integer price,
                              final Integer remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

}
