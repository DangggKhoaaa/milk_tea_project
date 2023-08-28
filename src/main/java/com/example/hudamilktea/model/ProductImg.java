package com.example.hudamilktea.model;

import com.example.hudamilktea.model.enums.FileType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonIgnore
    private Product product;
}
