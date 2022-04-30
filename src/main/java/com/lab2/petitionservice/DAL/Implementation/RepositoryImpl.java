package com.lab2.petitionservice.DAL.Implementation;

import com.lab2.petitionservice.DAL.Abstraction.Repository;

import java.util.List;

public class RepositoryImpl<TEntity, TIdentity> implements Repository<TEntity, TIdentity> {              //NOT USED

    @Override
    public List<TEntity> GetAll() {
        return null;
    }

    @Override
    public void Create(TEntity TEntity) {

    }

    @Override
    public TEntity Get(TIdentity Id) {
        return null;
    }

    @Override
    public void Update(TEntity TEntity) {

    }

    @Override
    public void Remove(TEntity TEntity) {

    }
}
