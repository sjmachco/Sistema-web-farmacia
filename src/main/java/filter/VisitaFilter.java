/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 *
 * @author TIVITO
 */
@WebFilter("/*")
public class VisitaFilter implements Filter {
    private static final Logger log = Logger.getLogger(VisitaFilter.class.getName());

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) 
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr;
        String uri = req.getRequestURI();
        
                // Ignorar recursos estáticos como CSS, JS, imágenes
        if (!uri.contains(".css") && !uri.contains(".js") && 
            !uri.contains(".png") && !uri.contains(".ico")) {
            log.info("Visita: " + req.getMethod() + " " + uri + 
                     " | IP: " + req.getRemoteAddr() + 
                     " | Hora: " + LocalDateTime.now());
        }
        fc.doFilter(sr, sr1);
    }
}
