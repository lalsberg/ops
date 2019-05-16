package com.lalsberg.ops.script;

import java.util.List;

import javax.persistence.EntityManager;

import groovy.lang.Script;

public class MyScript extends Script {

	@Override
	public void print(Object obj) {
		System.out.println("printando " + obj);
	}

	public Object runQuery(String query, EntityManager em) {
		System.out.println(query);

		List<?> resultList = (List<?>) em.createNativeQuery(query).getResultList();

		if (resultList.isEmpty()) {
			return null;
		}

		return resultList.get(0);
	}

	@Override
	public Object run() {
		// TODO Auto-generated method stub
		return null;
	}

}
