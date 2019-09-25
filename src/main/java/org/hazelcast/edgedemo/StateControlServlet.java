package org.hazelcast.edgedemo;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class StateControlServlet extends HttpServlet
{
    private static StateControlServlet instance = null;

    public static StateControlServlet getInstance( )
    {
        return instance;
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
