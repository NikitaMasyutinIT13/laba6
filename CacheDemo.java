package masyutin.models;

import masyutin.annotations.Cache;

/**
 * Демонстрационный класс для аннотации @Cache
 * Указывает кешируемые области
 */
@Cache({"users", "products", "orders"})
public class CacheDemo {



}