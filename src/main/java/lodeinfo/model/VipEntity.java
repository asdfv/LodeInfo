package lodeinfo.model;


public class VipEntity {

    private Long customersId;
    private String customersLastname;
    private String customersFirstname;
    private String paySum;
    private String payCount;

    public VipEntity() {
    }

    public Long getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Long customersId) {
        this.customersId = customersId;
    }

    public String getCustomersLastname() {
        return customersLastname;
    }

    public void setCustomersLastname(String customersLastname) {
        this.customersLastname = customersLastname;
    }

    public String getCustomersFirstname() {
        return customersFirstname;
    }

    public void setCustomersFirstname(String customersFirstname) {
        this.customersFirstname = customersFirstname;
    }

    public String getPaySum() {
        return paySum;
    }

    public void setPaySum(String paySum) {
        this.paySum = paySum;
    }

    public String getPayCount() {
        return payCount;
    }

    public void setPayCount(String payCount) {
        this.payCount = payCount;
    }
}
