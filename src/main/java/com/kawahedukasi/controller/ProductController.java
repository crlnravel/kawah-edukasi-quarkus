package org.acme.controller;

import org.acme.model.Product;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @GET
    public Response find(@QueryParam("id") Long id) {
        if (id == null) {
            List<Product> products = Product.findAll().list();

            return Response.ok(products).build();
        }

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
    @Transactional
    public Response update(@QueryParam("id") long id, Product updatedProduct) {
        Product product = Product.findById(id);

        if (product == null) return Response.status(404).entity("Can't find product by id: " + id).build();

        product.setName( updatedProduct.getName());
        product.setDescription( updatedProduct.getDescription());
        product.setType( updatedProduct.getType());
        product.setCount( updatedProduct.getCount());
        product.setPrice( updatedProduct.getPrice());

        product.persist();

        return Response.ok(product).build();
    }

    @DELETE
    @Transactional
    public Response deleteById(@QueryParam("id") long id) {
        Product.deleteById(id);

        return Response.ok().build();
    }

}
