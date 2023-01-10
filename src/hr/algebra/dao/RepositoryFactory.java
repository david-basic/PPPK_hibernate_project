/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dao.sql.HibernateRepository;

/**
 *
 * @author dbasi
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }
    
    private static final Repository REPOSITORY = new HibernateRepository();

    public static Repository getRepository() {
        return REPOSITORY;
    }
}
