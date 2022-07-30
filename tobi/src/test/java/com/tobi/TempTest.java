package com.tobi;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TempTest {
    @Test
    public void decorateEx() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("abc.csv");
        InputStream inputStream2 = new BufferedInputStream(new FileInputStream("abc.csv"));
    }

    @Test
    public void ProxyEx() {
        assertDoesNotThrow(() -> {
            List<String> list = new ArrayList<>();
            list.add("mskim");
        });

        assertThrows(UnsupportedOperationException.class, () -> {
            List<String> list = new ArrayList<>();
            Collection<String> proxyList = Collections.unmodifiableCollection(list);
            proxyList.add("mskim");
        });
    }
}
