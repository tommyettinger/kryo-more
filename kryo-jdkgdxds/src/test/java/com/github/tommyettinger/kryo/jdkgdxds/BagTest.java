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

public class BagTest {
    @Test
    public void testObjectBag() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectBag.class, new ObjectBagSerializer());

        ObjectBag<String> data = ObjectBag.with("-123.123", "0", "Four-Fifty Six", "0", "1.0", "-1.0", "0.000001");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectBag data2 = kryo.readObject(input, ObjectBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntBag() {
        Kryo kryo = new Kryo();
        kryo.register(IntBag.class, new IntBagSerializer());

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
    public void testLongBag() {
        Kryo kryo = new Kryo();
        kryo.register(LongBag.class, new LongBagSerializer());

        LongBag data = LongBag.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongBag data2 = kryo.readObject(input, LongBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testShortBag() {
        Kryo kryo = new Kryo();
        kryo.register(ShortBag.class, new ShortBagSerializer());

        ShortBag data = ShortBag.with(new short[]{-123, 0, 456, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ShortBag data2 = kryo.readObject(input, ShortBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testByteBag() {
        Kryo kryo = new Kryo();
        kryo.register(ByteBag.class, new ByteBagSerializer());

        ByteBag data = ByteBag.with(new byte[]{-123, 0, 45, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ByteBag data2 = kryo.readObject(input, ByteBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFloatBag() {
        Kryo kryo = new Kryo();
        kryo.register(FloatBag.class, new FloatBagSerializer());

        FloatBag data = FloatBag.with(-123.123f, 0f, 456.456f, 0, 1f, -1f, 0.000001f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FloatBag data2 = kryo.readObject(input, FloatBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testDoubleBag() {
        Kryo kryo = new Kryo();
        kryo.register(DoubleBag.class, new DoubleBagSerializer());

        DoubleBag data = DoubleBag.with(-123.123, 0, 456.456, 0, 1.0, -1.0, 0.000001);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            DoubleBag data2 = kryo.readObject(input, DoubleBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testBooleanBag() {
        Kryo kryo = new Kryo();
        kryo.register(BooleanBag.class, new BooleanBagSerializer());

        BooleanBag data = BooleanBag.with(true, false, false, true, false, true, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BooleanBag data2 = kryo.readObject(input, BooleanBag.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCharBag() {
        Kryo kryo = new Kryo();
        kryo.register(CharBag.class, new CharBagSerializer());

        CharBag data = CharBag.with("Hello, World!".toCharArray());

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CharBag data2 = kryo.readObject(input, CharBag.class);
            Assert.assertEquals(data, data2);
        }
    }

}
