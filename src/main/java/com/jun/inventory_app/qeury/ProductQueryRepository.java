package com.jun.inventory_app.qeury;

import com.jun.inventory_app.model.Product;
import com.jun.inventory_app.model.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository @RequiredArgsConstructor
public class ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Product> search(ProductSearchCondition condition){
        QProduct product = QProduct.product;
    }
}
