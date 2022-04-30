package com.lab2.petitionservice.DAL.Abstraction;

import com.lab2.petitionservice.BL.Models.Petition;

import java.util.List;
import java.util.UUID;

public interface Repository<TEntity, TIdentity> {                     //NOT USED

    public List<TEntity> GetAll();
    public void Create(TEntity entity);
    public TEntity Get(TIdentity Id);
    public void Update(TEntity entity);
    public void Remove(TEntity entity);

}
