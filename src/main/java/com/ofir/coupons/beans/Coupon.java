package com.ofir.coupons.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ofir.coupons.enums.Category;
import com.ofir.coupons.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "coupons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "company_id")
    private Company company;
    
    @Column(name = "category_id")
    @Enumerated(EnumType.ORDINAL)
    @NotNull(message = "category cannot be empty.")
    private Category category;
    
    @NotEmpty(message = "title cannot be empty.")
    @Size(min = 2, message = "title should be at least 2 characters long.")
    @Size(max = 60, message = "title should contain 60 characters at most.")
    private String title;
    
    @NotEmpty(message = "description cannot be empty.")
    @Size(min = 2, message = "description should be at least 2 characters long.")
    private String description;
    
    @Column(name = "start_date")
    @NotNull(message = "start date cannot be empty.")
    private Date startDate;
    
    @Column(name = "end_date")
    @NotNull(message = "end date cannot be empty.")
    private Date endDate;
    
    @Min(value = 0, message = "amount should be bigger than 0.")
    @Max(value = 1000000, message = "amount should be less than 1000000.")
    private int amount;
    
    @Min(value = 0, message = "price should be bigger than 0.")
    @Max(value = 100000, message = "price should be less than 100000.")
    private double price;
    
    @NotEmpty(message = "image cannot be empty.")
    private String image;

    //partial constructor
    public Coupon(Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    //partial constructor
    public Coupon(Company company, Category category, String title, String description, Date startDate, Date endDate, int amount, double price, String image) {
        this(category, title, description, startDate, endDate, amount, price, image);
        this.company = company;
    }
    
    @Override
    public String toString() {
        return String.format("Coupon- id: %d, company id: %d, category: %s, title: %s, description: %s, " +
                "start date: %s, end date: %s, amount: %d, price: %f, image: %s",
                id, company.getId(), Utils.convertEnumToString(category), title, description, startDate, endDate, amount, price, image);
    }

}
