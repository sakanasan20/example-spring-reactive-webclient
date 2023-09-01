package tw.niq.example.client;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.niq.example.dto.BeerDto;

public interface BeerClient {

	Flux<String> listBeersString();
	
	@SuppressWarnings("rawtypes")
	Flux<Map> listBeersMap();
	
	Flux<JsonNode> listBeersJson();
	
	Flux<BeerDto> listBeers();
	
	Mono<BeerDto> getBeerById(String beerId);
	
	Mono<BeerDto> createNewBeer(BeerDto beerDto);
	
	Mono<BeerDto> updateBeer(String beerId, BeerDto beerDto);
	
	Flux<BeerDto> findByBeerStyle(String beerStyle);
	
}
