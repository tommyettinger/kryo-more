/*
 * Copyright (c) 2022 See AUTHORS file.
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

public class MapTest {
    @Test
    public void testLongObjectMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongObjectMap.class, new LongObjectMapSerializer());

        LongObjectMap<Float> data = LongObjectMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongObjectMap<?> data2 = kryo.readObject(input, LongObjectMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongFloatMap.class, new LongFloatMapSerializer());

        LongFloatMap data = LongFloatMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongFloatMap data2 = kryo.readObject(input, LongFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongFloatOrderedMap.class, new LongFloatOrderedMapSerializer());

        LongFloatOrderedMap data = LongFloatOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongFloatOrderedMap data2 = kryo.readObject(input, LongFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testLongIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongIntMap.class, new LongIntMapSerializer());

        LongIntMap data = LongIntMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongIntMap data2 = kryo.readObject(input, LongIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongIntOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongIntOrderedMap.class, new LongIntOrderedMapSerializer());

        LongIntOrderedMap data = LongIntOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongIntOrderedMap data2 = kryo.readObject(input, LongIntOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
    @Test
    public void testLongLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongLongMap.class, new LongLongMapSerializer());

        LongLongMap data = LongLongMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongLongMap data2 = kryo.readObject(input, LongLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongLongOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(LongLongOrderedMap.class, new LongLongOrderedMapSerializer());

        LongLongOrderedMap data = LongLongOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongLongOrderedMap data2 = kryo.readObject(input, LongLongOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testIntFloatMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntFloatMap.class, new IntFloatMapSerializer());

        IntFloatMap data = IntFloatMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntFloatMap data2 = kryo.readObject(input, IntFloatMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntFloatOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntFloatOrderedMap.class, new IntFloatOrderedMapSerializer());

        IntFloatOrderedMap data = IntFloatOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntFloatOrderedMap data2 = kryo.readObject(input, IntFloatOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
    
    @Test
    public void testIntIntMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntIntMap.class, new IntIntMapSerializer());

        IntIntMap data = IntIntMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntIntMap data2 = kryo.readObject(input, IntIntMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntIntOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntIntOrderedMap.class, new IntIntOrderedMapSerializer());

        IntIntOrderedMap data = IntIntOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntIntOrderedMap data2 = kryo.readObject(input, IntIntOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testIntLongMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntLongMap.class, new IntLongMapSerializer());

        IntLongMap data = IntLongMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntLongMap data2 = kryo.readObject(input, IntLongMap.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntLongOrderedMap() {
        Kryo kryo = new Kryo();
        kryo.register(IntLongOrderedMap.class, new IntLongOrderedMapSerializer());

        IntLongOrderedMap data = IntLongOrderedMap.with(-1234567890L, 1.2f, 0L, 2.3f, 4567890123456789L, -3.4f, 0, -4.5f, 1L, -5.6f, 1, 6.7f, -1, -7.8f, 0, 8.9f);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntLongOrderedMap data2 = kryo.readObject(input, IntLongOrderedMap.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

}
