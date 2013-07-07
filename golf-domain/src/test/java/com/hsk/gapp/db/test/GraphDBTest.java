package com.hsk.gapp.db.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.hsk.gapp.logic.NodeManager;
import org.hsk.gapp.model.RelTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.index.Index;
import org.neo4j.test.TestGraphDatabaseFactory;

public class GraphDBTest {

	GraphDatabaseService testDb;
	NodeManager nodeManager;

	@Before
	public void beforeTest() {
		testDb = new TestGraphDatabaseFactory().newImpermanentDatabaseBuilder()
				.newGraphDatabase();
		assertNotNull(testDb);
		nodeManager = new NodeManager(testDb);
		assertNotNull(nodeManager);
	}

	@After
	public void afterTest() {
		testDb.shutdown();
	}

	@Test
	public void testCreateNodes() throws Exception {
		nodeManager.createNode();
	}

	@Test
	public void testCreateIndex() {
		nodeManager.createNodeIndex("nodes");
	}

	@Test
	public void testCreateRealtionship() throws Exception {
		Node n1 = nodeManager.createNode();
		Node n2 = nodeManager.createNode();
		nodeManager.createRelationship(n1, n2, RelTypes.KNOWS);
	}

	@Test
	public void testSearchIndex() throws Exception {
		Index<Node> index = nodeManager.createNodeIndex("nodes");
		Node n1 = nodeManager.createNode();
		nodeManager.updateNode(n1, "pesel", 12345);
		index.add(n1, "pesel", 12345);
		Node node = index.get("pesel", 12345).getSingle();
		assertEquals(12345, node.getProperty("pesel"));
	}
}
