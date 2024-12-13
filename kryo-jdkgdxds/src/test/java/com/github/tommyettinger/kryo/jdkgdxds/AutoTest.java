/*
 * Copyright (c) 2023-2024 See AUTHORS file.
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

package com.github.tommyettinger.kryo.jdkgdxds;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.tommyettinger.ds.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayOutputStream;
import java.util.EnumMap;

public class AutoTest {
    @Test
    public void testAutoLongDeque() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        LongDeque data = LongDeque.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongDeque data2 = kryo.readObject(input, LongDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testAutoIntBag() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        IntBag data = IntBag.with(-123, 0, 456, 0, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntBag data2 = kryo.readObject(input, IntBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testAutoObjectFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        ObjectFloatOrderedMap<ObjectList<String>> data = ObjectFloatOrderedMap.with(ObjectList.with("Cthulhu"),
                -123456.789f, ObjectList.with("lies"), 0f, ObjectList.with("deep"),
                4.56789f, ObjectList.with("in"), 0.0001f, ObjectList.with("Rl'yeh"), 1f, ObjectList.with("dreaming"), 1,
                ObjectList.with("of"), -1, ObjectList.with("waffles"), 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectFloatOrderedMap<?> data2 = kryo.readObject(input, ObjectFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    /**
     * This fails because EnumMap really needs a Class of an enum, and the generics don't provide it.
     * There's a private Class variable inside an EnumMap, but it needs reflection to access.
     */
    @Test
    @Ignore
    public void testAutoEnumMap() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        EnumMap<Character.UnicodeScript, String> data = new EnumMap<>(Character.UnicodeScript.class);
        data.put(Character.UnicodeScript.EGYPTIAN_HIEROGLYPHS, "Horus");
        data.put(Character.UnicodeScript.GREEK, "Zeus");
        data.put(Character.UnicodeScript.RUNIC, "Odin");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            EnumMap<?, ?> data2 = kryo.readObject(input, EnumMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testAutoObjectList() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        ObjectList<String> data = ObjectList.with("-123.123", "0", "Four-Fifty Six", "0", "1.0", "-1.0", "0.000001");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectList data2 = kryo.readObject(input, ObjectList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testAutoJunction() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        Junction<String> data = Junction.parse("(foo|bar|baz)^QUUX^woop woop");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Junction data2 = kryo.readObject(input, Junction.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testJunction() {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(true);
        JunctionSupport.registerJunction(kryo);

        Junction<String> data = Junction.parse("(foo|bar|baz)^QUUX^woop woop");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Junction data2 = kryo.readObject(input, Junction.class);
            Assert.assertEquals(data, data2);
        }
    }
}
