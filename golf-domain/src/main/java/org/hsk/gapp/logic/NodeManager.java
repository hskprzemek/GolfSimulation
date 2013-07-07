package org.hsk.gapp.logic;

import java.util.HashMap;
import java.util.Map.Entry;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;

public class NodeManager {

	private GraphDatabaseService service;

	public NodeManager(GraphDatabaseService service) {
		this.service = service;
	}

	public Node createNode() throws Exception {

		Transaction tx = service.beginTx();
		try {
			Node node = service.createNode();
			return node;
		} catch (Exception e) {
			tx.failure();
			throw e;
		} finally {
			tx.success();
		}
	}

	public Index<Node> createNodeIndex(String name) {
		return service.index().forNodes(name);
	}

	public void createRelationship(Node n1, Node n2, RelationshipType relation)
			throws Exception {
		Transaction tx = service.beginTx();
		try {
			n1.createRelationshipTo(n2, relation);
		} catch (Exception e) {
			tx.failure();
			throw e;
		} finally {
			tx.success();
		}
	}

	public Node updateNode(Node node, String property, Object value)
			throws Exception {
		Transaction tx = service.beginTx();
		try {
			node.setProperty(property, value);
			return node;
		} catch (Exception e) {
			tx.failure();
			throw e;
		} finally {
			tx.success();
		}
	}

	public Node updateNode(Node node, HashMap<String, Object> map)
			throws Exception {
		Transaction tx = service.beginTx();
		try {
			for (Entry<String, Object> entry : map.entrySet()) {
				node.setProperty(entry.getKey(), entry.getValue());
			}
			return node;
		} catch (Exception e) {
			tx.failure();
			throw e;
		} finally {
			tx.success();
		}
	}
}
