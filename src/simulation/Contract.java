package simulation;

/**
 * Clasa contine informatiile despre un contract
 * folosita in simularea jocului
 */
public class Contract {

    private Integer consumerId;
    private Integer price;
    private Integer remainedContractMonths;
    private Integer distributorId;

    /**
     *
     * @return Intoarce id-ul distribuitorului la care este facut
     * contractul
     */
    public Integer getDistributorId() {
        return distributorId;
    }

    /**
     *
     * Seteaza id-ul distribuitorului la care este facut
     * contractul
     */
    public void setDistributorId(final Integer distributorId) {
        this.distributorId = distributorId;
    }

    /**
     *
     * @return Intoarce id-ul consumatorului care face
     * contractul
     */
    public Integer getConsumerId() {
        return consumerId;
    }

    /**
     *
     * Seteaza id-ul consumatorului care face
     * contractul
     */
    public void setConsumerId(final Integer consumerId) {
        this.consumerId = consumerId;
    }

    /**
     *
     * @return Intoarce pretul contractul
     */
    public Integer getPrice() {
        return price;
    }

    /**
     *
     * Seteaza pretul contractul
     */
    public void setPrice(final Integer price) {
        this.price = price;
    }

    /**
     *
     * @return Intoarce numarul de luni ramase de plata
     */
    public Integer getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     *
     * Seteaza numarul de luni ramase de plata
     */
    public void setRemainedContractMonths(final Integer remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public Contract(final Integer consumerId, final Integer price,
                    final Integer remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
        // Initial un consumator nu este abonat nicaieri
        this.distributorId = -1;
    }

}
