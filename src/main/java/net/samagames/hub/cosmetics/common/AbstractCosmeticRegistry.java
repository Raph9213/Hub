package net.samagames.hub.cosmetics.common;

import net.samagames.hub.Hub;

import java.util.HashMap;
import java.util.logging.Level;

public abstract class AbstractCosmeticRegistry<T extends AbstractCosmetic>
{
    private final HashMap<String, T> elements;

    public AbstractCosmeticRegistry()
    {
        this.elements = new HashMap<>();
    }

    public abstract void register();

    public void registerElement(T element)
    {
        if(this.elements.containsKey(element.getKey()))
        {
            Hub.getInstance().log(Hub.getInstance().getCosmeticManager(), Level.SEVERE, "Cosmetic already registered with the database name '" + element.getKey() + "'!");
            return;
        }

        this.elements.put(element.getKey(), element);
    }

    public T getElementByStorageName(String databaseName)
    {
        if(this.elements.containsKey(databaseName))
            return this.elements.get(databaseName);
        else
            return null;
    }

    public HashMap<String, T> getElements()
    {
        return this.elements;
    }
}
