package lodeinfo.model;

public class SMSEntity {

    private Long customersId;
    private String customersFio;
    private String phoneNumber;
    private String cwdWorkersWorkDate;
    private String cwdWorkersWorkTimeBegin;
    private String workerFio;
    private String objectName;
    private String branchName;
    private String specialName;
    private String smsText;

    public SMSEntity() {
    }

    @Override
    public String toString() {
        return "Ув. " + customersFio + " приглашаем Вас " + cwdWorkersWorkDate + " в " + cwdWorkersWorkTimeBegin + " на " + objectName + " к " + specialName;
    }

    public Long getCustomersId() {
        return customersId;
    }

    public void setCustomersId(Long customersId) {
        this.customersId = customersId;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getCustomersFio() {
        return customersFio;
    }

    public void setCustomersFio(String customersFio) {
        this.customersFio = customersFio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCwdWorkersWorkDate() {
        return cwdWorkersWorkDate;
    }

    public void setCwdWorkersWorkDate(String cwdWorkersWorkDate) {
        this.cwdWorkersWorkDate = cwdWorkersWorkDate;
    }

    public String getCwdWorkersWorkTimeBegin() {
        return cwdWorkersWorkTimeBegin;
    }

    public void setCwdWorkersWorkTimeBegin(String cwdWorkersWorkTimeBegin) {
        this.cwdWorkersWorkTimeBegin = cwdWorkersWorkTimeBegin;
    }

    public String getWorkerFio() {
        return workerFio;
    }

    public void setWorkerFio(String workerFio) {
        this.workerFio = workerFio;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName;
    }
}
