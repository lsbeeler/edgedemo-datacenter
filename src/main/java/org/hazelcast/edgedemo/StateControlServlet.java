package org.hazelcast.edgedemo;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;


public class StateControlServlet extends HttpServlet
{
    private static final Logger LOG = Logger.getLogger("StateControlServlet");
    private static final Gson GSON = new Gson( );

    private static StateControlServlet instance = null;

    public static StateControlServlet getInstance( )
    {
        return instance;
    }

    private String stringifyRequestBody(HttpServletRequest request)
    {
        StringBuilder resultBuilder = new StringBuilder( );
        try {
            BufferedReader reader = request.getReader( );
            String line = reader.readLine( );

            while (line != null) {
                resultBuilder.append(line);
                resultBuilder.append('\n');

                line = reader.readLine( );
            }
        } catch (IOException e) {
            LOG.severe("StartServlet: stringifyRequestBody( ) generated " +
                    "IOException: " + e.getMessage( ));
        }

        return resultBuilder.toString( );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        Action postedAction = GSON.fromJson(req.getReader( ), Action.class);

        if (postedAction.equals(Action.RESET)) {
            resp.setStatus(200);

            AppConfiguration.HAZELCAST_INSTANCE.getMap(
                    AppConfiguration.AVERAGE_SPEED_MAP).clear( );
            AppConfiguration.HAZELCAST_INSTANCE.getMap(
                    AppConfiguration.COORDINATES_MAP).clear( );
            AppConfiguration.HAZELCAST_INSTANCE.getMap(
                    AppConfiguration.VIOLATIONS_MAP).clear( );

        } else {
            resp.setStatus(400);
            resp.getWriter( ).println("Unrecognized request: " +
                    stringifyRequestBody(req));
        }
    }

    @Override
    public void init( ) throws ServletException
    {
        super.init( );

        instance = this;
    }

    @Override
    public void destroy( )
    {
        super.destroy( );

        AppConfiguration.HAZELCAST_INSTANCE.shutdown( );
    }
}
