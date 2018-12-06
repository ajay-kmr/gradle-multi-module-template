package com.example.dao.repoService;

import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.dao.entity.BaseEntity;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hibernate.criterion.Restrictions.eq;

@CommonsLog
public abstract class BaseRepoService<T extends BaseEntity, ID extends Serializable> {

    private static final String ASC = "asc";
    private static final String defaultSortColumn = "createdDate";
    private static final String EMPTY = StringUtils.EMPTY;
    private static final String VALIDATION_ERROR_CODE = "ValidationError";
    @PersistenceContext
    protected EntityManager entityManager;
    @Autowired
    private MessageSource messageSource;

    protected abstract JpaRepository<T, ID> getRepository();

    abstract protected Class<T> getEntityClass();

    protected Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    protected Criteria getCriteria() {
        return getSession().createCriteria(getEntityClass())
                .add(eq("deleted", false));
    }

    protected Criteria addPagingAndSorting(Criteria criteria, DataTableRequestDTO dataTableSearchDTO) {
        addPagingAndSorting(criteria, dataTableSearchDTO.getMax(), dataTableSearchDTO.getOffset(),
                getOrderCriteria(dataTableSearchDTO.getSortOrder(), dataTableSearchDTO.getSortColumn()));
        return criteria;
    }

    public Order getOrderCriteria(String sortOrder, String sortColumn) {
        if (ASC.equalsIgnoreCase(sortOrder)) {
            return Order.asc(getSortColumn(sortColumn));
        } else {
            return Order.desc(getSortColumn(sortColumn));
        }
    }

    public String getSortColumn(String sortColumn) {
        if (StringUtils.isEmpty(sortColumn)) {
            sortColumn = defaultSortColumn;
        }
        return sortColumn;
    }

    private void addPagingAndSorting(Criteria criteria, int max, int offset, Order order) {
        criteria.setFirstResult(offset)
                .setMaxResults(max)
                .addOrder(order);
    }

    public long count() {
        return getRepository().count();
    }

    public <S extends T> long count(Example<S> example) {
        return getRepository().count(example);
    }

    public Long count(Criteria criteria) {
        criteria.setFirstResult(0);
        criteria.setProjection(Projections.rowCount());
        return (Long) criteria.uniqueResult();
    }

    public <S extends T> boolean exists(Example<S> example) {
        return getRepository().exists(example);
    }

    public T getOne(ID id) {
        return getRepository().getOne(id);
    }

    public Optional<T> findOne(ID id) {
        if (id == null) {
            return null;
        }
        return getRepository().findById(id);
    }

    public <S extends T> Optional<S> findOne(Example<S> example) {
        return getRepository().findOne(example);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    public List<T> findAll(Iterable<ID> ids) {
        return getRepository().findAllById(ids);
    }

    public <S extends T> List<S> findAll(Example<S> example) {
        return getRepository().findAll(example);
    }

    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return getRepository().findAll(example, sort);
    }

    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return getRepository().findAll(example, pageable);
    }

    public void flush() {
        getRepository().flush();
    }

    public <S extends T> S save(S entity) {
        return getRepository().save(entity);
    }

    public <S extends T> S saveAndFlush(S entity) {
        return getRepository().saveAndFlush(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        if (entities != null) {
            return getRepository().saveAll(entities);
        }
        return null;
    }

    public void delete(ID id) {
        if (id != null) {
            Optional<T> entity = getRepository().findById(id);
            if (entity.isPresent()) {
                delete(entity.get());
            }
        }
        //TODO Discuss whether this should be hard delete or soft delete.
//        getRepository().delete(id);

    }

    public void delete(T entity) {
        if (entity != null) {
            //TODO Discuss whether this should be hard delete or soft delete.
//        getRepository().delete(entity);
            entity.setDeleted(true);
            save(entity);
        }
    }

    public void delete(Iterable<? extends T> entities) {
        if (entities != null) {
            //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().delete(entities);
            entities.forEach(it -> it.setDeleted(true));
            save(entities);
        }
    }

    public void deleteAll() {
        //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().deleteAll();
        throw new IllegalArgumentException("Delete All is restricted..");
    }

    public void deleteInBatch(Iterable<T> entities) {
        //TODO Discuss whether this should be har delete or soft delete.
//        getRepository().deleteInBatch(entities);
        throw new IllegalArgumentException("DeleteInBatch is restricted..");
    }

    public void deleteAllInBatch() {
        //TODO Discuss whether this should be har delete or soft delete.
        getRepository().deleteAllInBatch();
    }

    public List<ID> findValidIds(List<ID> idsForValidation) {
        idsForValidation = idsForValidation.stream().filter(Objects::nonNull).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(idsForValidation)) {
            return Collections.emptyList();
        }
        List<T> result = getRepository().findAllById(idsForValidation);
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptyList();
        }
        return result.stream().map((T it) -> (ID) it.getId()).collect(Collectors.toList());
    }

    public <T> Stream<T> nullSafeStreamOf(Collection<T> collection) {
        return Optional.ofNullable(collection)
                .map(Collection::stream)
                .orElse(Stream.empty());
    }

    // Helper Method To read message from messages.properties file -- STARTS--

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(code, args, locale);
    }

    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return messageSource.getMessage(resolvable, locale);
    }

    public String getMessage(String messageKey) {
        return getMessage(messageKey, EMPTY);
    }

    public String getMessage(String messageKey, Object... args) {
        return messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }
}
