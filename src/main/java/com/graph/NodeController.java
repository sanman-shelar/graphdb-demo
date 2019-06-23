package com.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NodeController {

	@Autowired
	NodeRepository nodeRepository;

	@GetMapping("/rels")
	public Collection<Node> getRelationships(@Param("startNode") String startNode, @Param("endNode") String endNode) {
		return (Collection<Node>) nodeRepository.findRelationships(startNode, endNode);
	}

	@GetMapping
	public Collection<Node> getAllNodes() {
		return (Collection<Node>) nodeRepository.findAll();
	}

	// load mock data
	@Bean
	public void loadData() {

		nodeRepository.deleteAll();

		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		Node d = new Node();
		Node e = new Node();
		Node f = new Node();

		a.setTitle("A");
		b.setTitle("B");
		c.setTitle("C");
		d.setTitle("D");
		e.setTitle("E");
		f.setTitle("F");

		List<Path> aPaths = new ArrayList<>();
		aPaths.add(new Path(a, b));
		
		List<Path> bPaths = new ArrayList<>();
		bPaths.add(new Path(b, c));
		bPaths.add(new Path(b,f));
		

		List<Path> cPaths = new ArrayList<>();
		cPaths.add(new Path(c, d));

		List<Path> dPaths = new ArrayList<>();
		dPaths.add(new Path(e, d));

		a.setPaths(aPaths);
		b.setPaths(bPaths);
		c.setPaths(cPaths);
		d.setPaths(dPaths);

		nodeRepository.save(a);
		nodeRepository.save(b);
		nodeRepository.save(c);
		nodeRepository.save(d);
		nodeRepository.save(e);
		nodeRepository.save(f);

	}
}
