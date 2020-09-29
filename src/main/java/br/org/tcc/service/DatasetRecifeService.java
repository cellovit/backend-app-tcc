package br.org.tcc.service;

import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
public interface DatasetRecifeService {

    @GET
    // @Path("/groups/{groupId}/reports/{reportId}/GenerateToken")
    // @ClientHeaderParam(name = "Authorization", value = "{generateAADToken}")
//    @ClientHeaderParam(name = "Content-Type", value = "application/json")
    public Object getDatasetResult(@QueryParam("resource_id") String resource_id);

}
