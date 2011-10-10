package com.google.code.iriyanote;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public class PmFactory {
	private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private PmFactory() {
	}
	
	public static PersistenceManagerFactory getInstance() {
        return pmfInstance;
    }
}
