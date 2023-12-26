/**
 * 
 */
package com.soa.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.soa.business.UsuariosBusiness;
import com.soa.dto.Respuesta;
import com.soa.dto.Usuario;

/**
 * Class for receiving messages in an artemis queue.
 */
@Component
public class ArtemisListener {
    @Autowired
    private UsuariosBusiness business;

    @Autowired
    private JmsSender sender;

    /** Nombre de la cola de respuesta del microservicio. */
    @Value("${queue.name.out}")
    private String outQueueName;

    @JmsListener(destination = "${queue.name.in}")
    public void receive(String message) {
        System.out.println(String.format("Received message: %s",
                message));
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(message, Usuario.class);
        Respuesta respuesta = business.add(usuario);
        System.out.println(respuesta);
        try {
            sender.sendMessage(respuesta.toString(), outQueueName);
            System.out.println(String.format("Mensaje enviado: %s", 
                    respuesta.toString()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
