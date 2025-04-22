package ru.hpclab.hl.module1.service.cache;

import ru.hpclab.hl.module1.model.Customer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerCache {
    private static final Map<Long, Customer> cache = new HashMap<>();

    public static Optional<Customer> get(Long id) {
        return Optional.ofNullable(cache.get(id));
    }

    public static void put(Customer customer) {
        if (customer != null && customer.getId() != null) {
            cache.put(customer.getId(), customer);
        }
    }

    public static void remove(Long id) {
        cache.remove(id);
    }

    public static int size() {
        return cache.size();
    }

    public static void clear() {
        cache.clear();
    }

    public static boolean contains(Long id) {
        return cache.containsKey(id);
    }
}
