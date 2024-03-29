package com.powerreviews.project.persistence;

import com.powerreviews.project.controller.dto.RestaurantReviewDto;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity(name = "restaurant_review")
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"restaurant_id", "username"})
})
public class RestaurantReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private RestaurantEntity restaurant;

    @Size(min = 4, max = 200)
    @Column
    private String comment;

    @Range(min = 1, max = 5)
    @Column
    private Integer rating;

    @NotNull
    @Column
    private String username;

    @Column
    @CreationTimestamp
    private Timestamp timestamp;

    public RestaurantReviewDto toDto() {
        RestaurantReviewDto converted = new RestaurantReviewDto();
        converted.setTimestamp(timestamp);
        converted.setUsername(username);
        converted.setComment(comment);
        converted.setRating(rating);
        converted.setId(id);
        return converted;
    }


}
