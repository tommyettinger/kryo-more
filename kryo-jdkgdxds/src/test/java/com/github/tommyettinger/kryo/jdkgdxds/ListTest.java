/*
 * Copyright (c) 2022-2024 See AUTHORS file.
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

public class ListTest {
    @Test
    public void testObjectList() {
        Kryo kryo = new Kryo();
        kryo.register(ObjectList.class, new ObjectListSerializer());

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
    public void testIntList() {
        Kryo kryo = new Kryo();
        kryo.register(IntList.class, new IntListSerializer());

        IntList data = IntList.with(-123, 0, 456, 0, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntList data2 = kryo.readObject(input, IntList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongList() {
        Kryo kryo = new Kryo();
        kryo.register(LongList.class, new LongListSerializer());

        LongList data = LongList.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongList data2 = kryo.readObject(input, LongList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testShortList() {
        Kryo kryo = new Kryo();
        kryo.register(ShortList.class, new ShortListSerializer());

        ShortList data = ShortList.with(new short[]{-123, 0, 456, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ShortList data2 = kryo.readObject(input, ShortList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testByteList() {
        Kryo kryo = new Kryo();
        kryo.register(ByteList.class, new ByteListSerializer());

        ByteList data = ByteList.with(new byte[]{-123, 0, 45, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ByteList data2 = kryo.readObject(input, ByteList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testFloatList() {
        Kryo kryo = new Kryo();
        kryo.register(FloatList.class, new FloatListSerializer());

        FloatList data = FloatList.with(-123.123f, 0f, 456.456f, 0, 1f, -1f, 0.000001f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            FloatList data2 = kryo.readObject(input, FloatList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testDoubleList() {
        Kryo kryo = new Kryo();
        kryo.register(DoubleList.class, new DoubleListSerializer());

        DoubleList data = DoubleList.with(-123.123, 0, 456.456, 0, 1.0, -1.0, 0.000001);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            DoubleList data2 = kryo.readObject(input, DoubleList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testBooleanList() {
        Kryo kryo = new Kryo();
        kryo.register(BooleanList.class, new BooleanListSerializer());

        BooleanList data = BooleanList.with(true, false, false, true, false, true, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            BooleanList data2 = kryo.readObject(input, BooleanList.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testCharList() {
        Kryo kryo = new Kryo();
        kryo.register(CharList.class, new CharListSerializer());

        CharList data = CharList.with("Hello, World!".toCharArray());

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            CharList data2 = kryo.readObject(input, CharList.class);
            Assert.assertEquals(data, data2);
        }
    }

}
