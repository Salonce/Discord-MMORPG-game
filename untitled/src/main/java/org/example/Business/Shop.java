package org.example.Business;

import java.util.ArrayList;

public abstract class Shop {
    protected ArrayList<Item> itemsOnSale;
    abstract public String itemsAvailable();
}

