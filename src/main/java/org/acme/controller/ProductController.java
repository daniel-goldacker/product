package org.acme.controller;

import java.rmi.ServerError;
import java.util.List;

import org.acme.dto.ProductDTO;
import org.acme.service.ProductService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDTO> findAllProducts(){
        return productService.getAllProducts();
    }

    @POST
    @Transactional
    public Response saveProduct(ProductDTO productDTO){

        try {
            productService.createProduct(productDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}/")
    @Transactional
    public Response changeProduct(@PathParam("id") Long id, ProductDTO product){

        try {
            productService.changeProduct(id, product);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}/")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id){

        try {
            productService.deleteProduct(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
