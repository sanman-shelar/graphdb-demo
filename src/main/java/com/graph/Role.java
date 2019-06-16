package com.graph;

import java.util.Collection;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@RelationshipEntity(type = "ACTED_IN")
public class Role {
 
    @Id @GeneratedValue
    Long id;
 
    private Collection<String> roles;
 
    @StartNode
    private Person person;
 
    @EndNode
    private Movie movie;

    
    public Role() {
      super();
    }

    public Role(Long id, Collection<String> roles, Person person, Movie movie) {
      this.id = id;
      this.roles = roles;
      this.person = person;
      this.movie = movie;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Collection<String> getRoles() {
      return roles;
    }

    public void setRoles(Collection<String> roles) {
      this.roles = roles;
    }

    public Person getPerson() {
      return person;
    }

    public void setPerson(Person person) {
      this.person = person;
    }

    public Movie getMovie() {
      return movie;
    }

    public void setMovie(Movie movie) {
      this.movie = movie;
    } 
     
}