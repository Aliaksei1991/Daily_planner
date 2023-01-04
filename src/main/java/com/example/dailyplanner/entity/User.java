package com.example.dailyplanner.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table(name="users_info")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    @NotBlank(message="Name cannot be empty")
    private String username;

    @Column
    @Min(value = 18, message = "must be greater than 17")
    @Max(value = 90, message = "must be less than 91")
    private int age;

    @Column
    @Min(value = 150, message = "must be greater than 149")
    @Max(value = 210, message = "must be less than 211")
    private int height;

    @Column
    @Min(value=40, message = "must be greater than 39")
    @Max(value=200, message = "must be less than 201")
    private int weight;

    @Column
    @NotBlank(message="You should choose one variant")
    private String sex;

    @ManyToMany(cascade=CascadeType.ALL,
                fetch = FetchType.EAGER)
    @JoinTable(name="user_calories",
               joinColumns = @JoinColumn(name="user_id"),
               inverseJoinColumns = @JoinColumn(name="product_id"))
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private boolean active;

    @Column
    @NotBlank
    private String password;

    public User(String username, int age, int height, int weight, String sex) {
        this.username = username;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
    }

    public User() {
    }
}
