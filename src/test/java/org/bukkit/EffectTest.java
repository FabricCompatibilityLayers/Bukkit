package org.bukkit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class EffectTest {
	@Test
	public void getById() {
		for (Effect effect : Effect.values()) {
			assertThat(Effect.getById(effect.getId()), is(effect));
		}
	}
}
