package entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * 员工类
 */
public class Employee  {

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * ְ职位
     */
    private String employeeCareer;

    /**
     *电话
     */
    private String employeePhone;


    public Employee() {
    }

    public Employee(String name, String career, String phone) {
        this.employeeName = name;
        this.employeeCareer = career;
        this.employeePhone = phone;
    }

    public String getName() {
        return employeeName;
    }

    public void setName(String name) {
        this.employeeName = name;
    }

    public String getCareer() {
        return employeeCareer;
    }

    public void setCareer(String career) {
        this.employeeCareer = career;
    }

    public String getPhone() {
        return employeePhone;
    }

    public void setPhone(String phone) {
        this.employeePhone = phone;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return this.employeeName.equals(employee.employeeName) && this.employeeCareer.equals(employee.employeeCareer) && this.employeePhone.equals(employee.employeePhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.employeeName, this.employeeCareer, this.employeePhone);
    }

    @Override
    public String toString() {
        return this.employeeName+"  "+this.employeeCareer+"  "+this.employeePhone;
    }


}
