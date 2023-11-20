package org.bukkit;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BukkitMirrorTest {

	@Parameters(name = "{index}: {1}")
	public static List<Object[]> data() {
		return Arrays.stream(Server.class.getDeclaredMethods()).map(input -> new Object[] {
				input, input.toGenericString().substring("public abstract ".length())
				.replace("(", "{").replace(")", "}")
		}).collect(Collectors.toList());
	}

	@Parameter
	public Method server;

	@Parameter(1)
	public String name;

	private Method bukkit;

	@Before
	public void makeBukkit() throws Throwable {
		bukkit = Bukkit.class.getDeclaredMethod(server.getName(), server.getParameterTypes());
	}

	@Test
	public void isStatic() {
		assertTrue(Modifier.isStatic(bukkit.getModifiers()));
	}

	@Test
	public void isDeprecated() {
		assertThat(bukkit.isAnnotationPresent(Deprecated.class), is(server.isAnnotationPresent(Deprecated.class)));
	}

	@Test
	public void returnType() {
		assertThat(bukkit.getReturnType(), is((Object) server.getReturnType()));
		assertThat(bukkit.getGenericReturnType(), is(server.getGenericReturnType()));
	}

	@Test
	public void parameterTypes() {
		assertThat(bukkit.getGenericParameterTypes(), is(server.getGenericParameterTypes()));
	}

	@Test
	public void declaredException() {
		assertThat(bukkit.getGenericExceptionTypes(), is(server.getGenericExceptionTypes()));
	}
}
