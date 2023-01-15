package com.kawahedukasi.service;

import com.kawahedukasi.model.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.enterprise.context.ApplicationScoped;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ReportService {

    public byte[] exportReport() throws JRException {
        List<Product> products = Product.findAll().list();

        URL jrxmlFile = getClass().getClassLoader().getResource("products.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getPath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(products);

        Map<String, Object> param = new HashMap<>();
        param.put("Created by", "Carleano Ravelza Wongso");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
