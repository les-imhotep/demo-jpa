package dev.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.Test;

import dev.jpa.entites.Livre;

public class LivreTest {

	@Test
	public void test_find_livre() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa-pu");
		EntityManager em = emf.createEntityManager();

		// Recherche de livre par id
		Livre livre = em.find(Livre.class, 1);
		System.out.println(livre);

		// Recherche de livre par titre
		TypedQuery<Livre> queryListeLivres = em.createQuery("select l from Livre l where l.titre=:titre", Livre.class);
		queryListeLivres.setParameter("titre", "Germinal");
		List<Livre> listeLivres = queryListeLivres.getResultList();
		listeLivres.forEach(System.out::println);

		em.close();

		emf.close();
	}

}
