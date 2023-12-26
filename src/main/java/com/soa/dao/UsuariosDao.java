/**
 * 
 */
package com.soa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soa.dto.Usuario;

/**
 * Capa de acceso a datos.
 */
@Repository
public class UsuariosDao {

    /**
     * Objeto especializado en acceso a la BD.
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Agrega un usuario a la BD.
     * @param usuario Usuario a insertar.
     */
    public void insertar(Usuario usuario) {
        jdbcTemplate.execute("insert into usuario(login, password) values('"
                + usuario.getLogin() + "', '" + usuario.getPassword() + "')");
    }

    /**
     * Actualiza un usuario en la BD.
     * @param usuario Usuario a actualizar.
     */
    public void update(Usuario usuario) {
        jdbcTemplate.execute("update usuario set password ='" 
                + usuario.getPassword() + "' where login = '" 
                + usuario.getLogin() + "'");
    }
    
    /**
     * Borra un usuario de la BD.
     * @param usuario Usuario a borrar.
     */
    public void delete(Usuario usuario) {
        jdbcTemplate.execute("delete from usuario where login = '"
                + usuario.getLogin() + "'");
    }

    /**
     * Consulta de usuarios basados en el login.
     * @param usuario Objeto que contiene el login a consultar.
     */
//    public List<Usuario> qry(Usuario usuario) {
//        List<Usuario> resp = new ArrayList<>();
//        List<Map<String,Object>> list = 
//                jdbcTemplate.queryForList(
//                        "select id, login, password "
//                + "from usuario where login = '"
//                + usuario.getLogin() + "'");
//        System.out.println(list.getClass().getName());
//        for (Map<String, Object> registro : list) {
//            Usuario user = new Usuario();
//            user.setId((Integer) registro.get("id"));
//            user.setLogin((String) registro.get("login"));
//            user.setPassword((String) registro.get("password"));
//            resp.add(user);
//        }
//        return resp;
//    }


    /**
     * Consulta de usuarios basados en el login.
     * @param usuario Objeto que contiene el login a consultar.
     */
    public List<Usuario> query(Usuario usuario) {
        List<Usuario> resp = jdbcTemplate.query(
                "select id, login, password "
                + "from usuario where login = '"
                + usuario.getLogin() + "'", 
                new BeanPropertyRowMapper<Usuario>(Usuario.class));
        return resp;
    }

}
