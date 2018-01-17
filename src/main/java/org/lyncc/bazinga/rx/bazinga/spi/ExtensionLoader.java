package org.lyncc.bazinga.rx.bazinga.spi;

import org.lgl.core.spi.Spi.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 
 * fork from alibaba Dubbo 基于对原生的java SPI机制的加强
 * 
 * 
 *
 * @param <T>
 */
public class ExtensionLoader<T> {

	private static final Logger logger = LoggerFactory.getLogger(ExtensionLoader.class);
	private static ConcurrentMap<Class<?>, ExtensionLoader<?>> extensionLoaders = new ConcurrentHashMap<Class<?>, ExtensionLoader<?>>();

	private ConcurrentMap<String, T> singletonInstances = null;
	private ConcurrentMap<String, Class<T>> extensionClasses = null;

	private Class<T> type;
	private volatile boolean init = false;

	// spi path prefix
	private static final String PREFIX = "META-INF/services/";
	private ClassLoader classLoader;

	private ExtensionLoader(Class<T> type) {
		this(type, Thread.currentThread().getContextClassLoader());
	}

	public ExtensionLoader(Class<T> type, ClassLoader contextClassLoader) {
		this.type = type;
		this.classLoader = contextClassLoader;
	}

	public T getExtension(String name) {

		checkInit();

		try {
			Spi spi = type.getAnnotation(Spi.class);
			if (spi.scope() == Scope.SINGLETON) {
				return getSingletonInstance(name);
			} else {
				Class<T> clz = extensionClasses.get(name);
				if (clz == null) {
					return null;
				}

				return clz.newInstance();
			}
		} catch (Exception e) {
			failThrows(type, "Error when getExtension " + name, e);
		}

		return null;
	}

	private T getSingletonInstance(String name) throws InstantiationException, IllegalAccessException {
		T obj = singletonInstances.get(name);
		if (obj != null) {
			return obj;
		}

		Class<T> clz = extensionClasses.get(name);
		if (clz == null) {
			return null;
		}

		synchronized (singletonInstances) {
			obj = singletonInstances.get(name);
			if (obj != null) {
				return obj;
			}

			obj = clz.newInstance();
			singletonInstances.put(name, obj);
		}

		return obj;
	}

	private void checkInit() {
		if (!init) {
			loadExtensionClasses();
		}
	}

	private synchronized void loadExtensionClasses() {
		if (init) {
			return;
		}

		extensionClasses = loadExtensionClasses(PREFIX);
		singletonInstances = new ConcurrentHashMap<String, T>();
		init = true;
	}

	private ConcurrentMap<String, Class<T>> loadExtensionClasses(String prefix) {

		String fullName = prefix + type.getName();
		List<String> classNames = new ArrayList<String>();

		try {
			Enumeration<URL> urls;
			if (classLoader == null) {
				urls = ClassLoader.getSystemResources(fullName);
			} else {
				urls = classLoader.getResources(fullName);
			}
			if (urls == null || !urls.hasMoreElements()) {
				return new ConcurrentHashMap<String, Class<T>>();
			}
			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();

				parseUrl(type, url, classNames);
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"ExtensionLoader loadExtensionClasses error, prefix: " + prefix + " type: " + type.getClass(), e);
		}

		return loadClass(classNames);
	}

	@SuppressWarnings("unchecked")
	private ConcurrentMap<String, Class<T>> loadClass(List<String> classNames) {
		ConcurrentMap<String, Class<T>> map = new ConcurrentHashMap<String, Class<T>>();
		for (String className : classNames) {
			try {
				Class<T> clz;
				if (classLoader == null) {
					clz = (Class<T>) Class.forName(className);
				} else {
					clz = (Class<T>) Class.forName(className, true, classLoader);
				}

				checkExtensionType(clz);
				String spiName = getSpiName(clz);
				if (map.containsKey(spiName)) {
					failThrows(clz, ":Error spiName already exist " + spiName);
				} else {
					map.put(spiName, clz);
				}
			} catch (Exception e) {
				failLog(type, "Error load spi class", e);
			}
		}

		return map;

	}

	private void checkExtensionType(Class<T> clz) {
		checkClassPublic(clz);
		checkConstructorPublic(clz);
		checkClassInherit(clz);
	}

	private void checkClassInherit(Class<T> clz) {
		if (!type.isAssignableFrom(clz)) {
			failThrows(clz, "Error is not instanceof " + type.getName());
		}
	}

	private void checkClassPublic(Class<T> clz) {
		if (!Modifier.isPublic(clz.getModifiers())) {
			failThrows(clz, "Error is not a public class");
		}
	}

	private void checkConstructorPublic(Class<T> clz) {
		Constructor<?>[] constructors = clz.getConstructors();
		if (constructors == null || constructors.length == 0) {
			failThrows(clz, "Error has no public no-args constructor");
		}

		for (Constructor<?> constructor : constructors) {
			if (Modifier.isPublic(constructor.getModifiers()) && constructor.getParameterTypes().length == 0) {
				return;
			}
		}

		failThrows(clz, "Error has no public no-args constructor");
	}

	private String getSpiName(Class<?> clz) {
		SpiMeta spiMeta = clz.getAnnotation(SpiMeta.class);
		String name = (spiMeta != null && !"".equals(spiMeta.name())) ? spiMeta.name() : clz.getSimpleName();

		return name;
	}

	private void parseUrl(Class<T> type, URL url, List<String> classNames) {
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = url.openStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

			String line = null;
			int indexNumber = 0;
			while ((line = reader.readLine()) != null) {
				indexNumber++;
				parseLine(type, url, line, indexNumber, classNames);
			}
		} catch (Exception x) {
			failLog(type, "Error reading spi configuration file", x);
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException y) {
				failLog(type, "Error closing spi configuration file", y);
			}
		}
	}

	private void parseLine(Class<T> type, URL url, String line, int lineNumber, List<String> names) {
		int ci = line.indexOf('#');
		if (ci >= 0) {
			line = line.substring(0, ci);
		}

		line = line.trim();
		if (line.length() <= 0) {
			return;
		}

		if ((line.indexOf(' ') >= 0) || (line.indexOf('\t') >= 0)) {
			failThrows(type, url, lineNumber, "Illegal spi configuration-file syntax");
		}

		int cp = line.codePointAt(0);
		if (!Character.isJavaIdentifierStart(cp)) {
			failThrows(type, url, lineNumber, "Illegal spi provider-class name: " + line);
		}

		for (int i = Character.charCount(cp); i < line.length(); i += Character.charCount(cp)) {
			cp = line.codePointAt(i);
			if (!Character.isJavaIdentifierPart(cp) && (cp != '.')) {
				failThrows(type, url, lineNumber, "Illegal spi provider-class name: " + line);
			}
		}

		if (!names.contains(line)) {
			names.add(line);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
		checkInterfaceType(type);
		ExtensionLoader<T> loader = (ExtensionLoader<T>) extensionLoaders.get(type);
		if (null == loader) {
			loader = new ExtensionLoader<T>(type);
			extensionLoaders.putIfAbsent(type, loader);
		}
		return loader;
	}

	private static <T> void checkInterfaceType(Class<T> clz) {
		if (clz == null) {
			failThrows(clz, "Error extension type is null");
		}

		if (!clz.isInterface()) {
			failThrows(clz, "Error extension type is not interface");
		}

		if (!isSpiType(clz)) {
			failThrows(clz, "Error extension type without @Spi annotation");
		}
	}

	private static <T> void failLog(Class<T> type, String msg, Throwable cause) {
		logger.error(type.getName() + ": " + msg, cause);
	}

	private static <T> void failThrows(Class<T> type, URL url, int line, String msg) throws ServiceConfigurationError {
		failThrows(type, url + ":" + line + ": " + msg);
	}

	private static <T> boolean isSpiType(Class<T> clz) {
		return clz.isAnnotationPresent(Spi.class);
	}

	private static <T> void failThrows(Class<T> type, String msg) {
		throw new RuntimeException(type.getName() + ": " + msg);
	}

	private static <T> void failThrows(Class<T> type, String msg, Throwable cause) {
		throw new RuntimeException(type.getName() + ": " + msg, cause);
	}

}
