package dev.jpa.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// @Entity => déclare qu'une classe Java correspond à une table en base de données
@Entity
// @Table.name => spécifie le nom de la table
@Table(name = "CUSTOMER")
public class Customer {

	@Id // déclare à JPA quel champ représente la clé primaire
	@Column(name = "ID") // mapping champ <> colonne
	private Long id;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "LAST_NAME")
	private String nom;

	@Column(name = "FIRST_NAME")
	private String prenom;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", nom=" + nom + ", prenom=" + prenom + "]";
	}

}
