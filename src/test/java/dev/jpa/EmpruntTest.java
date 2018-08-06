package dev.jpa;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dev.jpa.entites.Client;
import dev.jpa.entites.Emprunt;
import dev.jpa.entites.Livre;

public class EmpruntTest {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Before
	public void setUp() {
		this.emf = Persistence.createEntityManagerFactory("demo-jpa-pu");
		this.em = emf.createEntityManager();
	}

	@Test
	public void test_recuperer_un_emprunt_par_id() {
		Emprunt emprunt = this.em.find(Emprunt.class, 7);
		System.out.println(emprunt);
	}

	@Test
	public void test_recuperer_un_emprunt_par_client() {

		List<Emprunt> empruntsTrouves = this.em
				.createQuery("select e from Emprunt e where e.client.id=:idClient", Emprunt.class)
				.setParameter("idClient", 2).getResultList();

		empruntsTrouves.forEach(System.out::println);
	}

	@Test
	public void inserer_emprunt_relations_persistantes() {
		Emprunt emp = new Emprunt();
		emp.setDateDebut(LocalDateTime.now());
		emp.setDelai(10);

		Livre livre1 = this.em.find(Livre.class, 1);
		Livre livre5 = this.em.find(Livre.class, 5);

		emp.setLivres(Arrays.asList(livre1, livre5));

		Client client1 = this.em.find(Client.class, 1);
		emp.setClient(client1);

		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		this.em.persist(emp);
		tx.commit();

	}

	@Test
	public void inserer_emprunt_relations_detachees() {
		Emprunt emp = new Emprunt();
		emp.setDateDebut(LocalDateTime.now());
		emp.setDelai(10);

		// livre 1 détaché
		Livre livre1 = new Livre();
		livre1.setId(1);

		// livre 5 détaché
		Livre livre5 = new Livre();
		livre5.setId(5);

		emp.setLivres(Arrays.asList(livre1, livre5));

		// client 1 détaché
		Client client1 = new Client();
		client1.setId(1);

		emp.setClient(client1);

		EntityTransaction tx = this.em.getTransaction();
		tx.begin();
		this.em.persist(emp);
		tx.commit();

	}

	@After
	public void tearDown() {
		em.close();
		emf.close();
	}

}
