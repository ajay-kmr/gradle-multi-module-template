package com.example.dao.repoService;

import com.example.commonmodel.dto.DataTableRequestDTO;
import com.example.commonmodel.dto.DataTableResponseDTO;
import com.example.commonmodel.dto.ProductDTO;
import com.example.dao.entity.Product;
import com.example.dao.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@CommonsLog
@AllArgsConstructor
public class ProductRepoService extends BaseRepoService<Product, Long> {

    private ProductRepository productRepository;

    @Override
    protected JpaRepository<Product, Long> getRepository() {
        return productRepository;
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @SuppressWarnings("unchecked")
    public DataTableResponseDTO<ProductDTO, List<ProductDTO>> searchProduct(DataTableRequestDTO<ProductDTO> requestDTO) {
        Criteria criteria = getCriteria();
        criteria.createAlias("catalog", "catalog_", JoinType.LEFT_OUTER_JOIN);
        ProductDTO productDTO = requestDTO.getQuery();
        if (productDTO != null) {
            if (productDTO.getId() != null) {
                criteria.add(Restrictions.eq("id", productDTO.getId()));
            }
            if (!StringUtils.isEmpty(productDTO.getName())) {
                criteria.add(Restrictions.ilike("name", productDTO.getName(), MatchMode.ANYWHERE));
            }
            if (!StringUtils.isEmpty(productDTO.getType())) {
                criteria.add(Restrictions.eq("type", productDTO.getType()));
            }
            if (productDTO.getCatalogId() != null) {
                criteria.add(Restrictions.eq("catalog_.id", productDTO.getCatalogId()));
            }
            if (!StringUtils.isEmpty(productDTO.getCatalogName())) {
                criteria.add(Restrictions.ilike("catalog_.name", productDTO.getCatalogName(), MatchMode.ANYWHERE));
            }
        }
        Long filteredFrom = count(criteria);
        addPagingAndSorting(criteria, requestDTO);
        addProjection(criteria);
        criteria.setResultTransformer(Transformers.aliasToBean(ProductDTO.class));
        List<ProductDTO> criteriaResult = (List<ProductDTO>) criteria.list();
        return DataTableResponseDTO.getInstance(count(), criteriaResult, filteredFrom, requestDTO);
    }

    private void addProjection(Criteria criteria) {
        criteria.setProjection(Projections.projectionList()
                .add(Projections.property("id").as("id"))
                .add(Projections.property("name").as("name"))
                .add(Projections.property("type").as("type"))
                .add(Projections.property("catalog_.id").as("catalogId"))
                .add(Projections.property("catalog_.name").as("catalogName"))
        );
    }
}
