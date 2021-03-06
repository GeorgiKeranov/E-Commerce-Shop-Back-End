package app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User user;

    private String status = "active";

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public int getPrice() {
        int totalPrice = 0;

        for(OrderItem orderItem: orderItems) {
            totalPrice += (orderItem.getQuantity() * orderItem.getProduct().getPrice());
        }

        return totalPrice;
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public String getEmail() {
        return user.getEmail();
    }
}
