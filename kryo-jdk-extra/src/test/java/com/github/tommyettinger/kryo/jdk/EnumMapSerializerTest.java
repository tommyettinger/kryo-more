/*
 * Copyright (c) 2023 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.tommyettinger.kryo.jdk;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

import com.esotericsoftware.kryo.Kryo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.esotericsoftware.kryo.serializers.EnumMapSerializer;

/**
 * A test case for the {@link EnumMapSerializer} from recent Kryo.
 * I'm not sure how to use it with multiple enum types, yet.
 * The more-general version from kryo-serializers does not work on Java 9 or higher.
 */
public class EnumMapSerializerTest {
	private static enum Vipers {
		SNAKE_CHARMER, BLACK_MAMBA, COTTONMOUTH, COPPERHEAD, CALIFORNIA_MOUNTAIN_SNAKE, SIDEWINDER;
	}

	private static enum Colors {
		BLUE, ORANGE, PINK, WHITE, BROWN, BLONDE;
	}

    private Kryo kryo;
    private EnumMap<Vipers, Set<String>> originalMap;
    
    @Before
    public void beforeTest() {
        kryo = new Kryo();
		kryo.register(Vipers.class);
		kryo.register(EnumMap.class, new EnumMapSerializer(Vipers.class));
		originalMap = new EnumMap<Vipers, Set<String>>(Vipers.class);
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test(expected = ClassCastException.class)
    public void testCopyEmpty() {
        EnumMap copy = kryo.copy(originalMap);
        // The next statement asserts that the key type of the copy is initialized correctly - 
        // it should throw the expected ClassCastException.
    	copy.put(Colors.BROWN, new HashSet<String>());
    }

	@Test
    public void testDeepCopy() {
    	kryo.register(java.util.HashSet.class);

		final Set<String> mambaAka = new HashSet<String>();
		mambaAka.add("Beatrix Kiddo");
		mambaAka.add("The Bride");
        originalMap.put(Vipers.BLACK_MAMBA, mambaAka);
        
		EnumMap<Vipers, Set<String>> copy = kryo.copy(originalMap);
        Assert.assertNotSame(originalMap, copy);
        Assert.assertTrue(copy.containsKey(Vipers.BLACK_MAMBA));
		Assert.assertNotSame(originalMap.get(Vipers.BLACK_MAMBA), copy.get(Vipers.BLACK_MAMBA));
        Assert.assertEquals(originalMap, copy);
    }
}