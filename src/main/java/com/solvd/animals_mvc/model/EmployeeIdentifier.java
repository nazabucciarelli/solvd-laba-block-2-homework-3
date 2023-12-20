package com.solvd.animals_mvc.model;

import java.util.Objects;

public class EmployeeIdentifier {
    private Long positionId;
    private Long departmentId;
    private String identificationCode;
    private String imageUrl;

    public EmployeeIdentifier(Long positionId, Long departmentId, String identificationCode, String imageUrl) {
        this.positionId = positionId;
        this.departmentId = departmentId;
        this.identificationCode = identificationCode;
        this.imageUrl = imageUrl;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getIdentificationCode() {
        return identificationCode;
    }

    public void setIdentificationCode(String identificationCode) {
        this.identificationCode = identificationCode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeIdentifier that = (EmployeeIdentifier) o;
        return Objects.equals(positionId, that.positionId) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(identificationCode, that.identificationCode) &&
                Objects.equals(imageUrl, that.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, departmentId, identificationCode, imageUrl);
    }

    @Override
    public String toString() {
        return "EmployeeIdentifier{" +
                "positionId=" + positionId +
                ", departmentId=" + departmentId +
                ", identificationCode='" + identificationCode + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
