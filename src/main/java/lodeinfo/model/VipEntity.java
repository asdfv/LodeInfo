package lodeinfo.model;


public class VipEntity {

    private Long customersId;
    private String customersLastname;
    private String customersFirstname;
    private int paySum;
    private int payCount;

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

    public int getPaySum() {
        return paySum;
    }

    public void setPaySum(int paySum) {
        this.paySum = paySum;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }
}
