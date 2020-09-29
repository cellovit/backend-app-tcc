package br.org.tcc.dto;

import java.io.Serializable;

public class DatastoreSearchResultDTO implements Serializable {

    private static final long serialVersionUID = 8549618190763614715L;

    private boolean includeTotal;
    private boolean distinct;
    private boolean plain;
    private Records records;
    private Integer total;

    public interface Records {
    }


}
