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

public class SetTest {
    @Test
    public void testIntSet() {
        Kryo kryo = new Kryo();
        kryo.register(IntSet.class, new IntSetSerializer());

        IntSet data = IntSet.with(-123, 0, 456, 0, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntSet data2 = kryo.readObject(input, IntSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testIntOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(IntOrderedSet.class, new IntOrderedSetSerializer());

        IntOrderedSet data = IntOrderedSet.with(-123, 0, 456, 0, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            IntOrderedSet data2 = kryo.readObject(input, IntOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testLongSet() {
        Kryo kryo = new Kryo();
        kryo.register(LongSet.class, new LongSetSerializer());

        LongSet data = LongSet.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongSet data2 = kryo.readObject(input, LongSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testLongOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(LongOrderedSet.class, new LongOrderedSetSerializer());

        LongOrderedSet data = LongOrderedSet.with(-1234567890L, 0L, 4567890123456789L, 0, 1L, 1, -1, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            LongOrderedSet data2 = kryo.readObject(input, LongOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }

    @Test
    public void testObjectSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjectSet.class);

        ObjectSet<String> data = ObjectSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectSet<?> data2 = kryo.readObject(input, ObjectSet.class);
            Assert.assertEquals(data, data2);
        }
    }

    @Test
    public void testObjectOrderedSet() {
        Kryo kryo = new Kryo();
        kryo.register(String.class);
        kryo.register(ObjectOrderedSet.class);

        ObjectOrderedSet<String> data = ObjectOrderedSet.with("Hello", "World", "!", "I", "am", "a", "test", "!", "yay");

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            ObjectOrderedSet<?> data2 = kryo.readObject(input, ObjectOrderedSet.class);
            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.order(), data2.order());
        }
    }
    @Test
    public void testOffsetBitSet() {
        Kryo kryo = new Kryo();
        kryo.register(OffsetBitSet.class, new OffsetBitSetSerializer());

        OffsetBitSet data = new OffsetBitSet(-123, 500);
        data.addAll(new int[]{-123, 0, 456, 0, 1, -1, 0});

        ByteArrayOutputStream baos = new ByteArrayOutputStream(32);
        Output output = new Output(baos);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            OffsetBitSet data2 = kryo.readObject(input, OffsetBitSet.class);
            Assert.assertEquals(data, data2);
        }
    }

}
