package com.kawahedukasi.service;

import com.kawahedukasi.model.Product;
import com.kawahedukasi.utils.FileMultipartDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.ApplicationScoped;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@ApplicationScoped
public class DocumentService {


    public void importProduct(FileMultipartDTO request) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request.file);

        XSSFWorkbook workbook = new XSSFWorkbook(byteArrayInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        sheet.removeRow(sheet.getRow(0));

        for (Row row : sheet) {
            Product product = new Product();

            product.setName(row.getCell(0).getStringCellValue());
            product.setDescription(row.getCell(1).getStringCellValue());
            product.setType(row.getCell(2).getStringCellValue());
            product.setCount((long) row.getCell(3).getNumericCellValue());
            product.setPrice((float) row.getCell(4).getNumericCellValue());

            product.persist();
        }
    }
}
