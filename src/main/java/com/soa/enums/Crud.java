/**
 * 
 */
package com.soa.enums;

import com.soa.tools.UnknownCrudException;

/**
 * Enumerador que representa las operaciones de BD.
 */
public enum Crud {
    CREATE("create"), 
    READ("read"), 
    UPDATE("update"), 
    DELETE("delete");

    /** Valor asociado al enum. */
    private String value;

    /**
     * @return the value
     */
    public final String getValue() {
        return value;
    }

    /**
     * Constructor del enumerador.
     * @param value Valor asociado a cada elemento.
     */
    Crud(String value) {
        this.value = value;
    }

    /**
     * Encontrar un elemento del enum por operacion.
     * @param operacion Operacion a buscar.
     * @return Elemento del enum correspondiente a la operacion.
     */
    public static Crud findByValue(String operacion) {
        Crud[] values = Crud.values();
        for (Crud crud : values) {
            if (operacion.equalsIgnoreCase(crud.getValue())) {
                return crud;
            }
        }
        throw new UnknownCrudException();
    }
}
