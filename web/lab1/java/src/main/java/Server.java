import check.Checker;
import com.fastcgi.FCGIInterface;
import validation.Validate;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;

class Server {
    public static void main (String[] args) {
        FCGIInterface fcgiInterface = new FCGIInterface();
        Validate v = new Validate();
        Checker checker = new Checker();

        while(fcgiInterface.FCGIaccept() >= 0) {

            String method = FCGIInterface.request.params.getProperty("REQUEST_METHOD");

            if (method.equals("GET")) {
                long time = System.nanoTime();
                String req = FCGIInterface.request.params.getProperty("QUERY_STRING");

                if (!Objects.equals(req, "")) {
                    LinkedHashMap<String, String> m = getValues(req);
                    boolean isShot;
                    boolean isValid;

                    try {
                        isValid = v.check(Integer.parseInt(m.get("x")), Double.parseDouble(m.get("y")), Integer.parseInt(m.get("r")));
                        isShot = checker.hit(Integer.parseInt(m.get("x")), Double.parseDouble(m.get("y")), Integer.parseInt(m.get("r")));

                    } catch (Exception e) {
                        System.out.println(err("Invalid data"));
                        continue;
                    }

                    if (isValid) {
                        String response = resp(isShot, m.get("x"), m.get("y"), m.get("r"), time);
                        System.out.println(response);
                    } else {
                        System.out.println(err(v.getErr()));
                    }
                } else {
                    System.out.println(err("fill"));
                }
            } else {
                System.out.println(err("method"));
            }
        }
    }

    private static LinkedHashMap<String, String> getValues(String inpString){
        String[] args = inpString.split("&");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (String s : args) {
            String[] arg = s.split("=");
            map.put(arg[0], arg[1]);
        }
        return map;
    }

    private static String resp(boolean isShoot, String x, String y, String r, long wt) {
        //logY(y);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String workTime = String.format(Locale.US,"%.2f", (double)(System.nanoTime() - wt) / 1_000_000);

        String content = """
        {"x":"%s","y":"%s","r":"%s","result":"%s","workTime":"%s","time":"%s"}
        """.formatted(x, y, r, isShoot, workTime, currentTime);
        return """
        Content-Type: application/json; charset=utf-8
        Content-Length: %d
        
        %s
        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    }


    private static String err(String msg) {
        String content = """
                {"error":"%s"}
                """.formatted(msg);
        return """
                Content-Type: application/json; charset=utf-8
                Content-Length: %d
                
                %s
                """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content);
    }

    /*private static void logY(String y) {
        try (FileWriter writer = new FileWriter("y_log.txt", true)) {
            writer.write("Y value: " + y + " | Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
