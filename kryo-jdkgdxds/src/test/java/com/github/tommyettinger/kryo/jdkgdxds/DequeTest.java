/*
 * Copyright (c) 2022-2023 See AUTHORS file.
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
import org.junit.Test;

import java.io.ByteArrayOutputStream;

public class DequeTest {
    @Test
    public void testObjectDeque() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectDeque.class, new ObjectDequeSerializer());

        ObjectDeque<String> data = ObjectDeque.with("-123.123", "0", "Four-Fifty Six", "0", "1.0", "-1.0", "0.000001");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectDeque data2 = kryo.readObject(input, ObjectDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntDeque() {
        Kryo kryo = new Kryo();
        kryo.register(IntDeque.class, new IntDequeSerializer());

        IntDeque data = IntDeque.with(-123, 0, 456, 0, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntDeque data2 = kryo.readObject(input, IntDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongDeque() {
        Kryo kryo = new Kryo();
        kryo.register(LongDeque.class, new LongDequeSerializer());

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
    public void testShortDeque() {
        Kryo kryo = new Kryo();
        kryo.register(ShortDeque.class, new ShortDequeSerializer());

        ShortDeque data = ShortDeque.with(new short[]{-123, 0, 456, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ShortDeque data2 = kryo.readObject(input, ShortDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testByteDeque() {
        Kryo kryo = new Kryo();
        kryo.register(ByteDeque.class, new ByteDequeSerializer());

        ByteDeque data = ByteDeque.with(new byte[]{-123, 0, 45, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ByteDeque data2 = kryo.readObject(input, ByteDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFloatDeque() {
        Kryo kryo = new Kryo();
        kryo.register(FloatDeque.class, new FloatDequeSerializer());

        FloatDeque data = FloatDeque.with(-123.123f, 0f, 456.456f, 0, 1f, -1f, 0.000001f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FloatDeque data2 = kryo.readObject(input, FloatDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testDoubleDeque() {
        Kryo kryo = new Kryo();
        kryo.register(DoubleDeque.class, new DoubleDequeSerializer());

        DoubleDeque data = DoubleDeque.with(-123.123, 0, 456.456, 0, 1.0, -1.0, 0.000001);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            DoubleDeque data2 = kryo.readObject(input, DoubleDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testBooleanDeque() {
        Kryo kryo = new Kryo();
        kryo.register(BooleanDeque.class, new BooleanDequeSerializer());

        BooleanDeque data = BooleanDeque.with(true, false, false, true, false, true, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BooleanDeque data2 = kryo.readObject(input, BooleanDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCharDeque() {
        Kryo kryo = new Kryo();
        kryo.register(CharDeque.class, new CharDequeSerializer());

        CharDeque data = CharDeque.with("Hello, World!".toCharArray());

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CharDeque data2 = kryo.readObject(input, CharDeque.class);
            Assert.assertEquals(data, data2);
        }
    }

}
