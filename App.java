import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", 8081), 0);
        server.createContext("/calc", new CalcHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Calculator API started on port 8081");
    }

    static class CalcHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            Map<String, String> params = parseQuery(exchange.getRequestURI().getQuery());

            double a = Double.parseDouble(params.getOrDefault("a", "0"));
            double b = Double.parseDouble(params.getOrDefault("b", "0"));
            String op = params.getOrDefault("op", "+");

            double result = switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> (b != 0 ? a / b : 0);
                default -> 0;
            };

            String response = "Result of " + a + " " + op + " " + b + " = " + result;

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private Map<String, String> parseQuery(String query) {
            if (query == null) return Map.of();
            return java.util.Arrays.stream(query.split("&"))
                .map(kv -> kv.split("="))
                .filter(kv -> kv.length == 2)
                .collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
        }
    }
}

