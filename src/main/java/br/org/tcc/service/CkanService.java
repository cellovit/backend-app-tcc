package br.org.tcc.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import minek.ckan.CkanClient;
import minek.ckan.CkanClientFactory;
import minek.ckan.v3.datastore.model.DatastoreSearchResult;
import minek.ckan.v3.datastore.model.enums.RecordsFormat;
import minek.ckan.v3.datastore.service.DatastoreService;
import minek.ckan.v3.datastore.service.command.DatastoreSearch;

@ApplicationScoped
public class CkanService {

    public void calldataservice() {

        try {

            CkanClientFactory factory = new CkanClientFactory("baseUrl", "apiKey");
            CkanClient client = factory.build();

            DatastoreSearch datastoreSearch = DatastoreSearch.builder()
                    .resourceId(UUID.fromString("9e323c0a-b40b-4bf7-a200-2b423f158120"))
                    .fields(Arrays.asList("hahaha1", "_id")).recordsFormat(RecordsFormat.objects).build();

            DatastoreService service = client.dataStoreService();

            DatastoreSearchResult result = service.datastoreSearch(datastoreSearch).execute().body();


        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    // DatastoreSearchResult result =
    // service.datastoreSearch(datastoreSearch).execute().body();

}
