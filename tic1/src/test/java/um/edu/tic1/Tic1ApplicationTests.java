package um.edu.tic1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import um.edu.tic1.entities.Movie;
import um.edu.tic1.services.MovieService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tic1ApplicationTests {

	@Autowired
	MovieService movieService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAddition() {
		Movie movie = new Movie("Rolando 3: Rolando Reloaded", "Best movie ever 11/10");
		movieService.save(movie);
		assertNotEquals(1, movieService.getMovieRepository().findAllByName("Rolando 3: Rolando Reloaded").size());
	}

}
