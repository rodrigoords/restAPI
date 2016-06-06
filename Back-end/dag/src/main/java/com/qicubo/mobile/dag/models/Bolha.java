package com.qicubo.mobile.dag.models;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Entity
@Table(name = "dag_bolha")
public class Bolha {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_bolha")
	private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="id_usuario")
	private Usuario usuarioCriacao;
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_tipo")
	private Tipo tipo;
	private String nome;
	private String descricao;
	private String dtHoraCriacao;
	private Latitude latitude;
	private Longitude longitude;
	private Index indice; 
	private Integer indRestrita;
	
	public Bolha(){
	}
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
	public Usuario getUsuarioCriacao() {
		return usuarioCriacao;
	}
	public void setUsuarioCriacao(Usuario usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDtHoraCriacao() {
		return dtHoraCriacao;
	}
	public void setDtHoraCriacao(String dtHoraCriacao) {
		this.dtHoraCriacao = dtHoraCriacao;
	}
	
	public Latitude getLatitude() {
		return latitude;
	}
	public void setLatitude(Latitude latitude) {
		this.latitude = latitude;
	    ajustaIndice();
	}
	
	public Longitude getLongitude() {
		return longitude;
	}
	public void setLongitude(Longitude longitude) {
		this.longitude = longitude;
        ajustaIndice();
	}
	
	public Index getIndice() {
        return indice;
    }

    public void setIndice(Index indice) {
        this.indice = indice;
    }

    public Integer getIndRestrita() {
		return indRestrita;
	}
	public void setIndRestrita(Integer indRestrita) {
		this.indRestrita = indRestrita;
	}
	
	/**
	 * Retorna a distancia da bolha em relação a os pontos passados como parametros, a unidade de retorno sera 
	 * de acordo com o parametro unit;
	 * @param latPointA
	 * @param longPointB
	 * @param unit : K - kilometros, N - Milhas Nauticas, M - Milhas
	 * @return
	 */
	public BigDecimal distancia(Latitude pointLat, Longitude pointLong, String unit){
	    
	    double latPointRadius = deg2rad(pointLat.doubleValue());
	    double longPointRadius = deg2rad(pointLong.doubleValue());
	    
	    double latPointBRadius = deg2rad(this.latitude.doubleValue());
	    double longPointBRadius = deg2rad(this.longitude.doubleValue());
	    
        double theta = longPointRadius - longPointBRadius;
        double dist = Math.sin(latPointRadius) * Math.sin(latPointBRadius) + Math.cos(latPointRadius) * Math.cos(latPointBRadius) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == "K") {
            dist = dist * 1.609344;
        } else if (unit == "N") {
            dist = dist * 0.8684;
        }
        
        return BigDecimal.valueOf(dist).setScale(3, RoundingMode.HALF_DOWN);
	}
	
    private double deg2rad(double deg){
        return deg * Math.PI / 180.0;
    }
    
    private double rad2deg(double rad) {
        return rad * 180 / Math.PI;
    }
    
    private void ajustaIndice(){
        if (indice != null){
            indice =  latitude.indexValue().add(longitude.indexValue());
        }
    }

}
