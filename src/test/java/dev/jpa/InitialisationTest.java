package dev.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import dev.jpa.entites.Customer;

public class InitialisationTest {

	@Test
	public void test_init_jpa() {

		// Etape 1 => Créer l'usine à sessions
		// => Créer une instance d'EntityManagerFactory (EMF)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-jpa-pu");

		// Etape 2 => Demander à l'usine une session
		// => L'usine fournit une instance d'EntityManager (session de travail)
		EntityManager em = emf.createEntityManager();

		Customer client = em.find(Customer.class, 4L);

		System.out.println(client);

		em.close();

		emf.close();
	}

}
