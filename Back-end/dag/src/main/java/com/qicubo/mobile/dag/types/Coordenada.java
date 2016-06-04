package com.qicubo.mobile.dag.types;

import java.math.BigDecimal;

public abstract class Coordenada extends Number{

    /**
     * 
     */
    private static final long serialVersionUID = -1543619056112355437L;
    
    public abstract BigDecimal bigDecimalValue();
    
    public abstract Coordenada subtract(Coordenada subtractElement);
    
    public abstract Coordenada add(Coordenada addElement);
    
    public abstract Index indexValue();
}
