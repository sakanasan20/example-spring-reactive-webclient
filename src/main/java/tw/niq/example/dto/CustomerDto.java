package tw.niq.example.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

	private String id;
	
	private String customerName;

	private LocalDateTime createdDate;
	
	private LocalDateTime last_modifiedDate;
	
}
