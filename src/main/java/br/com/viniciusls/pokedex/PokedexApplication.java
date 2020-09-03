package br.com.viniciusls.pokedex;

import br.com.viniciusls.pokedex.model.Pokemon;
import br.com.viniciusls.pokedex.repository.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedexApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, PokedexRepository repository) {
		return args -> {
			Flux<Pokemon> pokedexFlux = Flux.just(
					new Pokemon(null, "Bulbassaur", "Leaf", "Over Grow", 6.09),
					new Pokemon(null, "Charizard", "Fire", "Blaze", 90.05),
					new Pokemon(null, "Caterpie", "Worm", "Shell dust", 2.09),
					new Pokemon(null, "Blastoise", "Water", "Flux", 6.09)
			).flatMap(repository::save);

			pokedexFlux
					.thenMany(repository.findAll())
					.subscribe(System.out::println);
		};
	}
}
