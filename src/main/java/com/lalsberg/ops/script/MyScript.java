package com.lalsberg.ops.script;

import javax.persistence.EntityManager;

import groovy.lang.Script;

public class MyScript extends Script {

	@Override
	public void print(Object obj) {
		System.out.println("printando " + obj);
	}

	public Object runQuery(String query, EntityManager em) {
		System.out.println(query);

		Object bla = em.createNativeQuery(query).getSingleResult();

		System.out.println("bla " + bla);
		return bla;
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return null;
	}

}
