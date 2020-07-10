package com.stivenaguino.bean;

import com.stivenaguino.model.Persona;
import com.stivenaguino.service.IPersonaService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

    @Inject
    private IPersonaService personaService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captura = request.getPathInfo().substring(1);
        if (captura != null && !captura.equalsIgnoreCase("")) {
            int idPersona = Integer.parseInt(captura);
            Persona persona = new Persona(idPersona);
            persona = this.personaService.findById(persona);
            if (persona.getFoto() != null) {
                String mimeType = getServletContext().getMimeType("image/jpg");
                response.setContentType(mimeType);
                response.setContentLength(persona.getFoto().length);
                response.getOutputStream().write(persona.getFoto());
            }

        }
    }

}
