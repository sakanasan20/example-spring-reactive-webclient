package tw.niq.example.client;

import static org.awaitility.Awaitility.await;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.niq.example.dto.BeerDto;

@SpringBootTest
class BeerClientImplTest {
	
	@Autowired
	BeerClient beerClient;

	@BeforeEach
	void setUp() throws Exception {
	}
	
	public static BeerDto getTestBeerDto() {
		return BeerDto.builder()
				.beerName("Space Dust")
				.beerStyle("IPA")
				.price(BigDecimal.TEN)
				.quantityOnHand(12)
				.upc("123456")
				.build();
	}

	@Test
	void testListBeersString() {
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.listBeersString().subscribe(response -> {
			System.out.println(response);
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);	
	}
	
	@Test
	void testListBeersMap() {
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.listBeersMap().subscribe(response -> {
			System.out.println(response);
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);	
	}
	
	@Test
	void testListBeersJson() {
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.listBeersJson().subscribe(response -> {
			System.out.println(response);
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);	
	}
	
	@Test
	void testListBeers() {
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.listBeers().subscribe(response -> {
			System.out.println(response);
			atomicBoolean.set(true);
		});
		
		await().untilTrue(atomicBoolean);	
	}
	
	@Test
	void testGetBeerById() {

		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.listBeers()
			.flatMap(dto -> beerClient.getBeerById(dto.getId()))
			.subscribe(response -> {
				System.out.println(response);
				atomicBoolean.set(true);
			});
		
		await().untilTrue(atomicBoolean);	
	}
	
	@Test
	void testCreateNewBeer() {
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.createNewBeer(getTestBeerDto())
			.subscribe(response -> {
				System.out.println(response);
				atomicBoolean.set(true);
			});
		
		await().untilTrue(atomicBoolean);
	}
	
	@Test
	void testUpdateBeer() {
		final String newName = "New Name";
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.listBeers()
			.next()
			.doOnNext(beerDto -> beerDto.setBeerName(newName))
			.flatMap(dto -> beerClient.updateBeer(dto.getId(), dto))
			.subscribe(response -> {
				System.out.println(response);
				atomicBoolean.set(true);
			});
		
		await().untilTrue(atomicBoolean);
	}

	@Test
	void testFindByBeerStyle() {
		
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);	
		
		beerClient.findByBeerStyle("Pale Ale")
			.subscribe(response -> {
				System.out.println(response);
				atomicBoolean.set(true);
			});
		
		await().untilTrue(atomicBoolean);
	}

}
