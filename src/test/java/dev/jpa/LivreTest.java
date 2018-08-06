package dev.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.jpa.entites.Livre;

public class LivreTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Before
	public void setUp() {
		this.emf = Persistence.createEntityManagerFactory("demo-jpa-pu");
		this.em = emf.createEntityManager();
	}

	@Test
	public void test_find_livre() {
		// Recherche de livre par id
		Livre livre = em.find(Livre.class, 1);
		System.out.println(livre);
		// Recherche de livre par titre
		TypedQuery<Livre> queryListeLivres = em.createQuery("select l from Livre l where l.titre=:t1", Livre.class);
		queryListeLivres.setParameter("t1", "Germinal");
		List<Livre> listeLivres = queryListeLivres.getResultList();
		listeLivres.forEach(System.out::println);
	}

	@Test
	public void test_insertion() {

		// objet nouveauLivre à l'état TRANSIENT
		Livre nouveauLivre = new Livre();

		nouveauLivre.setTitre(RandomStringUtils.randomAlphabetic(10));
		nouveauLivre.setAuteur("Diginamic");

		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		this.em.persist(nouveauLivre);
		tx.commit();
		// nouveauLivre est désormais PERSISTENT
	}

	@After
	public void tearDown() {
		// FIN SESSION
		em.close();
		emf.close();
	}

}
