package com.kawahedukasi.service;

import com.kawahedukasi.model.Product;
import io.quarkus.scheduler.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class SchedulerService {

    private final Logger logger;

    @Inject
    private EntityManager entityManager;

    public SchedulerService() {
        logger = LoggerFactory.getLogger(SchedulerService.class);
    }

    @Scheduled(every = "1h")
    @Transactional
    public void deleteItemZeroCount() {
        Query query = entityManager.createNativeQuery("SELECT * FROM product_schema.product WHERE \"count\" = 0", Product.class);

        List<Product> products = query.getResultList();

        for (Product product: products) {
            product.delete();
        }

        logger.info("0 count products has been deleted!");
    }
}
