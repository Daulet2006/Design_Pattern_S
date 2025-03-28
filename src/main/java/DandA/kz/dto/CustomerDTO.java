package DandA.kz.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String contactInfo;
    private List<OrderDTO> orders;
}