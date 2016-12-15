package com.qicubo.mobile.dag.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qicubo.mobile.dag.controllers.BolhaRestURIConstants;
import com.qicubo.mobile.dag.controllers.TipoRestURIConstants;

import org.springframework.http.MediaType;

import java.io.IOException;

import java.nio.charset.Charset;

public class TestUtil {
	
	public static final String BASE_URL                     = "http://localhost";
	public static final String BASE_URI                     = BolhaRestURIConstants.BASE_URI;
	public static final String GET_ALL_BOLHAS_URI           = BolhaRestURIConstants.BASE_URI;
	public static final String GET_BOLHAS_BY_ID_URI         = BolhaRestURIConstants.BASE_URI + BolhaRestURIConstants.GET_BOLHA_BY_ID;
	public static final String GET_BOLHAS_IN_RANGE_URI      = BolhaRestURIConstants.BASE_URI + BolhaRestURIConstants.GET_BOLHA_IN_RANGE;
	public static final String GET_BOLHAS_BY_USER_LOGIN_URI = BolhaRestURIConstants.BASE_URI + BolhaRestURIConstants.GET_BOLHA_BY_USER_LOGIN;
	public static final String CREATE_BOLHA_URI             = BolhaRestURIConstants.BASE_URI;
	public static final String DELETE_BOLHA                 = BolhaRestURIConstants.BASE_URI + BolhaRestURIConstants.DELETE_BOLHA;
	
	
	public static final String BASE_TIPO_URI    = TipoRestURIConstants.URI_BASE;
	public static final String GET_TIPO_BY_NAME = TipoRestURIConstants.URI_BASE + TipoRestURIConstants.GET_TIPO_BY_NAME;
	
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsBytes(object);

    }

    public static String createStringWithLength(int length) {

        StringBuilder builder = new StringBuilder();

        for (int index = 0; index < length; index++) {

            builder.append("a");

        }

        return builder.toString();
    }
}
