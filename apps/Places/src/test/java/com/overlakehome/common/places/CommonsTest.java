package com.overlakehome.common.places;

import static org.apache.commons.lang.StringUtils.isAllLowerCase;
import static org.apache.commons.lang.StringUtils.isAllUpperCase;
import static org.apache.commons.lang.StringUtils.isAlpha;
import static org.apache.commons.lang.StringUtils.isAlphanumeric;
import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNumeric;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import junit.framework.Assert;

import org.junit.Test;

public class CommonsTest {
    // Boilerplate Java code busters ...
    @Test
    public void testStringUtils() {
        Assert.assertTrue(isAlpha("abcde"));
        Assert.assertTrue(isNumeric("12345"));
        Assert.assertTrue(isAlphanumeric("12345abc"));

        Assert.assertTrue(isBlank(""));
        Assert.assertTrue(isBlank(null));

        Assert.assertTrue(isAllLowerCase("abcdef"));
        Assert.assertTrue(isAllUpperCase("ABCDEF"));
    }

    public class ResourceProvider {
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Map<String, String> data = new HashMap<String, String>();

        public String getResource(String key) throws Exception {
            lock.readLock().lock();
            try {
                return data.get(key);
            } finally {
                lock.readLock().unlock();
            }
        }

        public void refresh() throws Exception {
            lock.writeLock().lock();
            try {
                // reload settings...
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public class ResourceProviderV2 {
        private final ResourceController controller = new ResourceController();
        private final Map<String, String> data = new HashMap<String, String>();

        public String getResource(final String key) throws Exception {
            return controller.invokeReader(new Callable<String>() {
                public String call() throws Exception {
                    return data.get(key);
                }
            });
        }

        public void refresh() throws Exception {
            controller.invokeWriter(new Callable<Void>() {
                public Void call() throws Exception {
                    // reload settings
                    return null;
                }
            });
        }
    }

    public class ResourceController {
        private final ReadWriteLock lock = new ReentrantReadWriteLock();

        public <T> T invokeReader(Callable<T> reader) throws Exception {
            lock.readLock().lock();
            try {
                return reader.call();
            } finally {
                lock.readLock().unlock();
            }
        }

        public <T> T invokeWriter(Callable<T> writer) throws Exception {
            lock.writeLock().lock();
            try {
                return writer.call();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    // Java 7 has general availability 2011/07/28 http://openjdk.java.net/projects/jdk7/
    public void testJava6() throws IOException {
        // Traditional error handling
        try {
            doSomething();
        } catch (UnsupportedOperationException e) {
            handleError(e);
        } catch (IllegalStateException e) {
            handleError(e);
        } catch (IllegalArgumentException e) {
            handleError(e);
        }

        // Modern error handling in Java 7
//      try {
//          doSomething();
//      } catch (UnsupportedOperationException
//               | IllegalStateException
//               | IllegalArgumentException e) {
//          handleError(e);
//      }

        String path = "~/.gitconfig";
        int read = -1;
        FileReader reader = new FileReader(path);
        try {
            read = reader.read();
        } finally {
            reader.close();
        }

        assert read > 0;

        // Modern resource management in Java 7
//      try (FileReader reader = new FileReader(path)) {
//          read = reader.read();
//      }

//      class MyResource implements AutoCloseable {
//          String getResource() {
//              return "some resource";
//          }
//
//          @Override
//          public void close() throws Exception {
//              // closing
//          }
//      }
//
//      try (MyResource resource = new MyResource()) {
//          return resource.getResource();
//      }
    }

    private static void doSomething() {
    }

    public static void handleError(Exception e) {
    }
}

