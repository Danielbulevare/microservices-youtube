package com.dan.api_gateway_microservices_youtube.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.dan.api_gateway_microservices_youtube.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	/*
	 * Cada vez que la solicitud llegue al Api Gateway, quiero que el Api Gateway valide el token. Primero
	 * verifica si el usuario pasa el apecto del token del encabezado o no; luego lo validara.
	 * Para implementar esta validacion necesitamos  implementar un filtro en el Apy Gateway, una vez
	 * que la solicitud llega al filtro, el filtro del Api Gateway verificara si el usuario pasa el token
	 * o no, si lo pasa hace la llamada para validar el token; si es valido envia esa solicitud al
	 * microservicio correspondiente
	 *  
	 * Cualquier solicitud que el usuario envie primero ira al filtro de autenticación para verificar
	 * si el usuario se le da el encabezado de autoriazción o no; si esta presente obtiene el token, y
	 * si ese token es valido, entonces le permite acceder al endpoint que solicito (microservicios);
	 * de los contrario lanzara una excepcion
	 */
	
    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
        	/*
        	 * Verifica si la ruta o solicitud es segura
        	 */
            if (validator.isSecured.test(exchange.getRequest())) {
                //Verifica si contiene el token en la cabecera o no
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Falta el encabezado de autorización.");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                
                try {
                    //Se podría validar el token por medio de una llamada a un api que tengas (pero no es buena idea hacerlo, ya que podría se hackeada)
                    //template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    
                	/*
                	 * La mejor forma para validar el token es agregando logica relacionada con el token
                	 * de validación. Esto ayuda a reducir la llamada de red
                	 */
                	jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("Aceeso invalido...!");
                    throw new RuntimeException("Acceso no autorizado a la aplicación");
                }
            }
            
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}