package com.kawahedukasi.controller;

import com.kawahedukasi.model.Product;
import com.kawahedukasi.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    public Response findAll() {
        List<Product> products = Product.findAll().list();

        return Response.ok(products).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") long id) {
        Product product = Product.findById(id);

        if (product == null) return Response.status(404).entity("Can't find product by id: " + id).build();

        return Response.ok(product).build();
    }

    @POST
    @Transactional
    public Response save(Product newProduct) {
        newProduct.persist();

        return Response.ok(newProduct).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateById(@PathParam("id") long id, Product updatedProduct) {
        Product oldProduct = Product.findById(id);

        if (oldProduct == null) return Response.status(404).entity("Can't find product by id: " + id).build();

        productService.updateAndPersist(oldProduct, updatedProduct);

        return Response.ok(oldProduct).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteById(@PathParam("id") long id) {
        Product product = Product.findById(id);

        if (product == null) return Response.status(404).entity("Can't find product by id: " + id).build();

        product.delete();

        return Response.ok(product).build();
    }
}
