package br.com.viniciusls.pokedex.repository;

import br.com.viniciusls.pokedex.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {
}
