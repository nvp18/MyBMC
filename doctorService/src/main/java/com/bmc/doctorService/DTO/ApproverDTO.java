package com.bmc.doctorService.DTO;

public class ApproverDTO {

    private String approvedBy;
    private String approverComments;

    public ApproverDTO(String approvedBy, String approverComments) {
        this.approvedBy = approvedBy;
        this.approverComments = approverComments;
    }

    public ApproverDTO() {
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApproverComments() {
        return approverComments;
    }

    public void setApproverComments(String approverComments) {
        this.approverComments = approverComments;
    }
}
