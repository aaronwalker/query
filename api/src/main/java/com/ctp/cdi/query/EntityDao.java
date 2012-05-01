package com.ctp.cdi.query;

import com.ctp.cdi.query.criteria.Criteria;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;

/**
 * Base DAO interface. All methods are implemented by the query extension.
 * @author thomashug
 *
 * @param <E>   Entity type.
 * @param <PK>  Primary key type.
 */
public interface EntityDao<E, PK extends Serializable> {

    /**
     *
     * @return  Returns the criteria
     */
    Criteria criteria();

    /**
     * Persist (new entity) or merge the given entity.
     * @param entity            Entity to save.
     * @return                  Returns the modified entity.
     */
    E save(E entity);

    /**
     * {@link #save(Object)}s the given entity and flushes the persistence context afterwards.
     * @param entity            Entity to save.
     * @return                  Returns the modified entity.
     */
    E saveAndFlush(E entity);
    
    /**
     * Convenience access to {@link EntityManager#remove(Object)}.
     * @param entity            Entity to remove.
     */
    void remove(E entity);
    
    /**
     * Convenience access to {@link EntityManager#refresh(Object)}.
     * @param entity            Entity to refresh.
     */
    void refresh(E entity);

    /**
     * Convenience access to {@link EntityManager#flush()}.
     */
    void flush();

    /**
     * Entity lookup by primary key. Convenicence method around {@link EntityManager#find(Class, Object)}.
     * @param primaryKey        DB primary key.
     * @return                  Entity identified by primary or null if it does not exist.
     */
    E findBy(PK primaryKey);

    /**
     * Lookup all existing entities of entity class {@code <E>}.
     * @return                  List of entities, empty if none found.
     */
    List<E> findAll();

    /**
     * Lookup a range of existing entities of entity class {@code <E>} with support for pagination.
     * @param start             The starting position.
     * @param max               The maximum number of results to return
     * @return                  List of entities, empty if none found.
     */
    List<E> findAll(int start, int max);
    
    /**
     * Query by example - for a given object and a specific set of properties.
     * TODO: Can we put something like that in a producer and have queries restricted by example?
     * TODO: As far as I remember Hibernate does this by all non-null properties.
     * @param example           Sample entity. Query all like.
     * @param attributes        Which attributes to consider for the query.
     * @return                  List of entities matching the example, or empty if none found.
     */
    List<E> findBy(E example, SingularAttribute<E, ?>... attributes);

    /**
     * Query by example - for a given object and a specific set of properties with support for pagination.
     * TODO: Can we put something like that in a producer and have queries restricted by example?
     * TODO: As far as I remember Hibernate does this by all non-null properties.
     * @param example           Sample entity. Query all like.
     * @param start             The starting position.
     * @param max               The maximum number of results to return
     * @param attributes        Which attributes to consider for the query.
     * @return                  List of entities matching the example, or empty if none found.
     */
    List<E> findBy(E example, int start, int max, SingularAttribute<E, ?>... attributes);

    /**
     * Query by example - for a given object and a specific set of properties using a like operator for Strings.
     * TODO: Can we put something like that in a producer and have queries restricted by example?
     * TODO: As far as I remember Hibernate does this by all non-null properties.
     * @param example           Sample entity. Query all like.
     * @param attributes        Which attributes to consider for the query.
     * @return                  List of entities matching the example, or empty if none found.
     */
    List<E> findByLike(E example, SingularAttribute<E, ?>... attributes);

    /**
     * Query by example - for a given object and a specific set of properties using a like operator for Strings with support for pagination.
     * TODO: Can we put something like that in a producer and have queries restricted by example?
     * TODO: As far as I remember Hibernate does this by all non-null properties.
     * @param example           Sample entity. Query all like.
     * @param start             The starting position.
     * @param max               The maximum number of results to return
     * @param attributes        Which attributes to consider for the query.
     * @return                  List of entities matching the example, or empty if none found.
     */
    List<E> findByLike(E example, int start, int max, SingularAttribute<E, ?>... attributes);


    /**
     * Entity lookup by primary key. Convenicence method around {@link EntityManager#find(Class, Object)}.
     * @param primaryKey        DB primary key.
     * @return                  Entity identified by primary or null if it does not exist.
     */


    /**
     * Count all existing entities of entity class {@code <E>}.
     * @return                  Counter.
     */
    Long count();

    /**
     * Count existing entities of entity class {@code <E>}
     * with for a given object and a specific set of properties..
     * @param example           Sample entity. Query all like.
     * @param attributes        Which attributes to consider for the query.
     *
     * @return                  Counter.
     */
    Long count(E example, SingularAttribute<E, ?>... attributes);

    /**
     * Count existing entities of entity class using the like operator for String attributes {@code <E>}
     * with for a given object and a specific set of properties..
     * @param example           Sample entity. Query all like.
     * @param attributes        Which attributes to consider for the query.
     *
     * @return                  Counter.
     */
    Long countLike(E example, SingularAttribute<E, ?>... attributes);

}
