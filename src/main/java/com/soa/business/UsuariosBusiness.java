/**
 * 
 */
package com.soa.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soa.dao.UsuariosDao;
import com.soa.dto.Request;
import com.soa.dto.Respuesta;
import com.soa.dto.Usuario;
import com.soa.enums.Crud;
import com.soa.tools.UnknownCrudException;

/**
 * Clase para concatenación de datos personales.
 */
@Component
public class UsuariosBusiness {
    /** Objeto de acceso a datos. */
    @Autowired
    private UsuariosDao usuariosDao;

    /**
     * Forma el nombre completo de una persona.
     * @param persona Persona recibida.
     * @return Respuesta con el nombre completo.
     */
    public Respuesta add(Usuario usuario) {
        Respuesta respuesta = new Respuesta();
        try {
            usuariosDao.insertar(usuario);
            respuesta.setMessage("OK");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setMessage("Error en BD al insertar");
        }
        return respuesta;
    }

    /**
     * Consulta usuarios por login.
     * @param usuario
     * @return
     */
    public Respuesta qry(Usuario usuario) {
        Respuesta respuesta = new Respuesta();
        try {
            List<Usuario> list = usuariosDao.query(usuario);
            respuesta.setMessage("OK");
            respuesta.setUsuarios(list);
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setMessage("Error en BD al consultar login: " 
                    + usuario.getLogin());
        }
        return respuesta;
    }

    /**
     * Procesa las operaciones CRUD de una BD.
     * @param request Objeto de petición.
     * @return Respuesta obtenida de la BD.
     */
    public Respuesta procesar(Request request) {
        Respuesta respuesta = new Respuesta();
        
        
        try {
            String operacion = request.getOperacion();
            Crud crud = Crud.findByValue(operacion);
//            throw new NullPointerException();
            switch(crud) {
            case CREATE: 
                usuariosDao.insertar(request.getUsuario());
                break;
            case READ:
                List<Usuario> list = usuariosDao.query(request.getUsuario());
                respuesta.setUsuarios(list);
                break;
            case UPDATE: 
                usuariosDao.update(request.getUsuario());
                break;
            case DELETE:
                usuariosDao.delete(request.getUsuario());
            default: break;
            }
            respuesta.setMessage("OK");
        } catch (UnknownCrudException e) {
            e.printStackTrace();
            respuesta.setMessage("Operacion no reconocida");
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setMessage("Error en BD: " + e.getMessage());
        }
        return respuesta;
    }
}
