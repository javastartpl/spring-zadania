package pl.javastart.devicrent4.components.customer;

import pl.javastart.devicrent4.components.device.Device;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(length = 11)
    private String pesel;
    @Column(name = "id_number", length = 10)
    private String idNumber;
    @ManyToMany(mappedBy = "customers")
    private List<Device> rentDevices = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public List<Device> getRentDevices() {
        return rentDevices;
    }

    public void setRentDevices(List<Device> rentDevices) {
        this.rentDevices = rentDevices;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", imię='" + firstName + '\'' +
                ", nazwisko='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", nr dowodu='" + idNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(pesel, customer.pesel) &&
                Objects.equals(idNumber, customer.idNumber) &&
                Objects.equals(rentDevices, customer.rentDevices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, pesel, idNumber, rentDevices);
    }
}
