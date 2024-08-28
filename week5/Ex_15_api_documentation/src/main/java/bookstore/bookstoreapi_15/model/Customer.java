package bookstore.bookstoreapi_15.model;

import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Customer {
    private Long id;
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 1, max = 255)
    private String address;

    @Version
    private int version;
    // Constructor, getters, and setters

    public Customer() {}

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }
}
