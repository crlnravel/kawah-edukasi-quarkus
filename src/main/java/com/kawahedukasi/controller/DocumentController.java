package com.kawahedukasi.controller;

import com.kawahedukasi.service.DocumentService;
import com.kawahedukasi.service.ReportService;
import com.kawahedukasi.utils.FileMultipartDTO;
import net.sf.jasperreports.engine.JRException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/document")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public class DocumentController {

    @Inject
    ReportService reportService;

    @Inject
    DocumentService documentService;

    @Path("/product")
    @Transactional
    public Response importProduct(@MultipartForm FileMultipartDTO request) {
        try {
            documentService.importProduct(request);
            return Response.ok("Every data has been inserted!").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
        }
    }

    @GET
    @Produces("application/pdf")
    @Path("/report")
    public Response generateReport() {
        try {
            return Response.ok(reportService.exportReport()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad Request").build();
        }
    }
}
