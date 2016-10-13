package web;

import java.io.IOException;

public class StaticProcessor {

    public void process(Request request, Response response) {
        try {
//            if (request.getUriTop().equals("POST")) {

                response.sendStaticResource();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}