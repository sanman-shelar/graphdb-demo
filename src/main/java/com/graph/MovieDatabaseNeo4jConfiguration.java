package com.graph;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;


@Configuration
@ComponentScan("com.graph")
@EnableNeo4jRepositories(basePackages = "com.graph")
public class MovieDatabaseNeo4jConfiguration {

	@Bean(destroyMethod = "shutdown")
	public GraphDatabaseService graphDatabaseService() {
		GraphDatabaseService graphDatabaseService = new GraphDatabaseFactory()
				.newEmbeddedDatabaseBuilder(new File("target/graph.db"))
				.setConfig(GraphDatabaseSettings.forbid_shortestpath_common_nodes, "false")
				.newGraphDatabase();

		return graphDatabaseService;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
        EmbeddedDriver driver = new EmbeddedDriver(graphDatabaseService());
		return new SessionFactory(driver, "com.graph");
	}

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(getSessionFactory());
	}
}
