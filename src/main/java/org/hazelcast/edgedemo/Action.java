package org.hazelcast.edgedemo;


import java.util.Objects;


/**
 * Represents an application control action. Right now, the only action
 * supported is RESET.
 */
public final class Action
{
    public static final Action RESET = new Action("RESET");

    private String actionId;

    public Action( )
    {
        this.actionId = "NOOP";
    }

    public Action(String actionId)
    {
        this.actionId = actionId;
    }

    public String getActionId( )
    {
        return actionId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass( ) != o.getClass( ))
            return false;

        Action action = (Action) o;

        return Objects.equals(actionId, action.actionId);
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash(actionId);
    }

    @Override
    public String toString( )
    {
        return "Action{" +
                "actionId='" + actionId + '\'' +
                '}';
    }
}
