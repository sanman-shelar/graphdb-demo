package com.graph;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends Neo4jRepository<Node, Long> { 
     
    @Query("MATCH r=(m1:Node {title: {0}})-[*]->(m2: Node {title: {1}}) RETURN r")    
    Collection<Node> findRelationships(String startNode, String endNode);
 

}