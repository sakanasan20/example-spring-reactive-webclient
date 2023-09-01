package tw.niq.example.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	private String id;
	
	private String beerName;

	private String beerStyle;

	private String upc;
	
	private Integer quantityOnHand;
	
	private BigDecimal price;

	private LocalDateTime createdDate;
	
	private LocalDateTime last_modifiedDate;
	
}
