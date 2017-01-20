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

import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.qicubo.mobile.dag.dto.BolhaDTO;
import com.qicubo.mobile.dag.types.Index;
import com.qicubo.mobile.dag.types.Latitude;
import com.qicubo.mobile.dag.types.Longitude;

@Entity
@Table(name = "dag_bolha")
public class Bolha implements Identifiable<Long>{
	
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
	
	@JsonCreator
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
	    this.ajustaIndice();
	}
	
	public Longitude getLongitude() {
		return longitude;
	}
	public void setLongitude(Longitude longitude) {
		this.longitude = longitude;
        this.ajustaIndice();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((dtHoraCriacao == null) ? 0 : dtHoraCriacao.hashCode());
		result = prime * result + ((indRestrita == null) ? 0 : indRestrita.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuarioCriacao == null) ? 0 : usuarioCriacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bolha other = (Bolha) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtHoraCriacao == null) {
			if (other.dtHoraCriacao != null)
				return false;
		} else if (!dtHoraCriacao.equals(other.dtHoraCriacao))
			return false;
		if (indRestrita == null) {
			if (other.indRestrita != null)
				return false;
		} else if (!indRestrita.equals(other.indRestrita))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuarioCriacao == null) {
			if (other.usuarioCriacao != null)
				return false;
		} else if (!usuarioCriacao.equals(other.usuarioCriacao))
			return false;
		return true;
	}
    
    public BolhaDTO toDTO(){
    	BolhaDTO bolhaDTO = new BolhaDTO();
    	
    	bolhaDTO.setDescricao(this.descricao);
    	bolhaDTO.setDtHoraCriacao(this.dtHoraCriacao);
    	bolhaDTO.setIndRestrita(this.indRestrita.toString());
    	bolhaDTO.setLatitude(this.latitude.toString());
    	bolhaDTO.setLongitude(this.longitude.toString());
    	bolhaDTO.setNome(this.nome);
    	bolhaDTO.setTipoNome(this.tipo.getNome());
    	bolhaDTO.setUsuarioCriacaoLogin(this.usuarioCriacao.getLogin());
    	
    	return bolhaDTO;
    }
    
    public static Bolha fromDTO(BolhaDTO bolhaDTO){
    	
        Bolha bolha = new Bolha();
        
        bolha.setDescricao(bolhaDTO.getDescricao());
        bolha.setDtHoraCriacao(bolhaDTO.getDtHoraCriacao());
        bolha.setIndRestrita(new Integer(bolhaDTO.getIndRestrita()));
        bolha.setLatitude(new Latitude(bolhaDTO.getLatitude()));
        bolha.setLongitude(new Longitude(bolhaDTO.getLongitude()));
        bolha.setNome(bolhaDTO.getNome());
        
    	return bolha;
    }
}
