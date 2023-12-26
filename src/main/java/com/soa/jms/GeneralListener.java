/**
 * ñlkwdjñlASDJÑLKa jddAJDS Jads.
 */

package com.soa.jms;

import com.google.gson.Gson;
import com.soa.business.UsuariosBusiness;
import com.soa.dto.Request;
import com.soa.dto.Respuesta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Class for receiving messages in an artemis queue.
 */
@Component
public class GeneralListener {
    @Autowired
    private UsuariosBusiness business;

    @Autowired
    private JmsSender sender;

    /** Nombre de la cola de respuesta del microservicio. */
    @Value("${general.queue.name.out}")
    private String outQueueName;

    @JmsListener(destination = "${general.queue.name.in}")
    public void receive(String message) {
        System.out.println(String.format("Received message: %s",
                message));
        Gson gson = new Gson();
        Request request = gson.fromJson(message, Request.class);
        Respuesta respuesta = business.procesar(request);
        try {
            sender.sendMessage(respuesta.toString(), outQueueName);
            System.out.println(String.format("Mensaje procesado: %s", 
                    respuesta.toString()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
