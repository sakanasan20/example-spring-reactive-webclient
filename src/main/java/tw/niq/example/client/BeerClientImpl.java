package tw.niq.example.client;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tw.niq.example.dto.BeerDto;

@Service
public class BeerClientImpl implements BeerClient {
	
	private static final String BEER_PATH = "/api/v3/beers";
	private static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
	private final WebClient webClient;

	public BeerClientImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	@Override
	public Flux<String> listBeersString() {
		return webClient.get().uri(BEER_PATH)
				.retrieve()
				.bodyToFlux(String.class);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Flux<Map> listBeersMap() {
		return webClient.get().uri(BEER_PATH)
				.retrieve()
				.bodyToFlux(Map.class);
	}
	
	@Override
	public Flux<JsonNode> listBeersJson() {
		return webClient.get().uri(BEER_PATH)
				.retrieve()
				.bodyToFlux(JsonNode.class);
	}
	
	@Override
	public Flux<BeerDto> listBeers() {
		return webClient.get().uri(BEER_PATH)
				.retrieve()
				.bodyToFlux(BeerDto.class);
	}

	@Override
	public Mono<BeerDto> getBeerById(String beerId) {
		return webClient.get().uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(beerId))
				.retrieve()
				.bodyToMono(BeerDto.class);
	}

	@Override
	public Mono<BeerDto> createNewBeer(BeerDto beerDto) {
		return webClient.post().uri(BEER_PATH).body(Mono.just(beerDto), BeerDto.class)
				.retrieve()
				.toBodilessEntity()
				.flatMap(voidResponseEntity -> Mono.just(voidResponseEntity.getHeaders().get("Location").get(0)))
				.map(path -> path.split("/")[path.split("/").length - 1])
				.flatMap(this::getBeerById);
	}

	@Override
	public Mono<BeerDto> updateBeer(String beerId, BeerDto beerDto) {
		return webClient.put().uri(uriBuilder -> uriBuilder.path(BEER_PATH_ID).build(beerId)).body(Mono.just(beerDto), BeerDto.class)
				.retrieve()
				.toBodilessEntity()
				.flatMap(voidResponseEntity -> getBeerById(beerId));
	}

	@Override
	public Flux<BeerDto> findByBeerStyle(String beerStyle) {
		return webClient.get().uri(uriBuilder -> uriBuilder.path(BEER_PATH).queryParam("beerStyle", beerStyle).build())
				.retrieve()
				.bodyToFlux(BeerDto.class);
	}

}
