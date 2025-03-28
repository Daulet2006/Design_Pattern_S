package DandA.kz.model;

import com.fasterxml.jackson.annotation.JsonBackReference; // Add this import
import com.fasterxml.jackson.annotation.JsonManagedReference; // Add this import
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Add this annotation
    private List<Order> orders;
}